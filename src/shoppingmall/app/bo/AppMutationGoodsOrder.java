package shoppingmall.app.bo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import shoppingmall.po.UserBalance;
import shoppingmall.wxpay.WPayCallPayRequest;
import shoppingmall.wxpay.WPayQueryOrderResponse;
import shoppingmall.wxpay.WPayService;
import shoppingmall.wxpay.WPayBuildOrderRequest;
import shoppingmall.wxpay.WPayBuildOrderResponse;
import shoppingmall.alipay.APayAccountTransferRequest;
import shoppingmall.alipay.APayAccountTransferResponse;
import shoppingmall.alipay.APayService;
import shoppingmall.alipay.APayTradeAppPayRequest;
import shoppingmall.alipay.APayTradeQueryRequest;
import shoppingmall.alipay.APayTradeQueryResponse;
import shoppingmall.alipay.Currency;
import shoppingmall.dao.DAOBalance;
import shoppingmall.dao.DAOGoods;
import shoppingmall.dao.DAOGoodsOrder;
import shoppingmall.dao.DAOGoodsRefund;
import shoppingmall.dao.DAOUser;
import shoppingmall.exception.DataValidateException;
import shoppingmall.input.ParamGoodsReview;
import shoppingmall.input.ParamPrepay;
import shoppingmall.input.ParamUserReview;
import shoppingmall.po.Goods;
import shoppingmall.po.GoodsOrder;
import shoppingmall.po.GoodsOrderItem;
import shoppingmall.po.GoodsOrderRefund;
import shoppingmall.po.GoodsShoppingCart;
import shoppingmall.po.GoodsReview;
import shoppingmall.po.Pay;
import shoppingmall.po.PayState;
import shoppingmall.po.UserAddress;
import shoppingmall.po.UserAuth;
import shoppingmall.po.UserReview;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Refund;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoodsOrder")
public class AppMutationGoodsOrder extends BOBase {
	
	protected final Logger log = Logger.getLogger(this.getClass());

	@GraphQLField("prepay")
	public Pay prepay(@GraphQLArgument("param") ParamPrepay param)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		DAOUser daoUser = getDAO().getDAOUser();
		UserAddress addr = daoUser.getUserAddress(param.getAddrno());
		if(addr == null){
			throw new DataValidateException("the.addr.no.can.not.find");
		}
		long userseq = getEnvironment().getUser().getUserseq();
		List<GoodsShoppingCart> carts = daoGoodsOrder.queryGoodsShoppingCartList(userseq);
		long orderseq = nextOrderseq(daoGoodsOrder);
		GoodsOrder order = new GoodsOrder(addr);
		order.setOrderseq(orderseq);
		order.setBuyerseq(userseq);
		order.setPostkind(param.getPostkind());
		order.setPostfee(param.getPostfee());
		int total = 0;
		String title = null;
		Long sellerseq = null;
		for(long goodsseq : param.getGoodsseqs()){
			Goods goods = daoGoods.getGoods(goodsseq);
			if(goods == null){
				throw new DataValidateException("the.goods.can.not.find");
			}
			if(title == null){
				title = goods.getGoodsname() + " 等商品";
			}
			if(sellerseq == null){
				sellerseq = goods.getSellerseq();
			}else if(sellerseq != goods.getSellerseq()){
				throw new DataValidateException("one.order.must.be.same.seller");
			}
			GoodsOrderItem orderItem = buildGoodsOrderItem(carts, goods);
			total += orderItem.getPrice() * orderItem.getCount();
			orderItem.setGoodsseq(orderseq);
			daoGoodsOrder.addGoodsOrderItem(orderItem);
		}
		order.setSellerseq(sellerseq);
		order.setTitle(title);
		order.setTotal(total);
		daoGoodsOrder.addGoodsOrder(order);
		if("W".equals(param.getChannel())){
			String prepayId = buildPrepayId(order.getOutTradeNo(), title, order.getTotal(), param.getIP());
			order.setPrepayid(prepayId);
		}
		return callpay(param.getChannel(), order);
	}
	
	private Pay callpay(String channel, GoodsOrder order) throws Exception {
		String outTradeNo = order.getOutTradeNo();
		if("W".equals(channel)){
			WPayCallPayRequest call = new WPayCallPayRequest(null);
			call.setPrepayid(order.getPrepayid());
			String queryStr = call.toXml();
			log.debug("WPayCallPayRequest:" + queryStr);
			Pay bean = new Pay();
			bean.setAppid(WPayCallPayRequest.getAppid());
			bean.setTimestamp(call.getTimestamp());
			bean.setPartnerid(call.getPartnerid());
			bean.setPackage(call.getPackage());
			bean.setNoncestr(call.getNoncestr());
			bean.setSign(call.getSign());
			bean.setOutTradeNo(outTradeNo);
			bean.setOrderseq(order.getOrderseq());
			return bean;
		}else if("A".equals(channel)){
			APayTradeAppPayRequest call = new APayTradeAppPayRequest();
			call.setSubject(order.getTitle());
			call.setOut_trade_no(outTradeNo);
			call.setTimeout_express("10m");
			call.setTotal_amount(Currency.parseFen(order.getTotal()));
			call.setGoods_type("1");
			String queryStr = call.toQueryString();
			log.debug("APayTradeAppPayRequest:" + queryStr);
			Pay bean = new Pay();
			bean.setQueryStr(queryStr);
			bean.setOutTradeNo(outTradeNo);
			bean.setOrderseq(order.getOrderseq());
			return bean;
		}
		throw new DataValidateException("not.support.channel", channel);
	}
	
	private String buildPrepayId(String outTradeNo, String title, int amount, String ip)throws Exception {
		WPayBuildOrderRequest request = new WPayBuildOrderRequest(null);
		if(title != null && title.length() > 32){
			title = title.substring(0, 32);
		}
		request.setBody(title);
		request.setDetail(title);
		request.setOutTradeNo(outTradeNo);
		request.setTotalFee(amount);
		request.setSpbillCreateIp(ip);
		request.setTradeType("APP");
		WPayService wpay = new WPayService();
		WPayBuildOrderResponse response = wpay.buildOrder(request);
		return response.getPrepay_id();
	}
	
	private GoodsOrderItem buildGoodsOrderItem(List<GoodsShoppingCart> carts, Goods goods)throws Exception{
		for(GoodsShoppingCart cart : carts){
			if(cart.getGoodsseq() == goods.getGoodsseq()){
				GoodsOrderItem item = new GoodsOrderItem(goods);
				item.setCount(cart.getCount());
				carts.remove(cart);
				return item;
			}
		}
		throw new DataValidateException("the.goodsseq.can.not.find.in.carts");
	}

	private long nextOrderseq(DAOGoodsOrder daoGoodsOrder)throws Exception{
		Long key = daoGoodsOrder.getMaxOrderseq();
		if(key == null){
			key = GoodsOrder.MIN_GOODS_ORDER_SEQ;
		}
		key++;
		return key;
	}

	@GraphQLField("payAgain")
	public Pay payAgain(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("channel") String channel)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		if(order.getPaytime() != null){
			throw new DataValidateException("goods.order.had.payed");
		}
		if(!GoodsOrder.STATUS_UNPAY.equals(order.getStatus())){
			throw new DataValidateException("goods.order.can.not.payed");
		}
		return callpay(channel, order);
	}
	
	@GraphQLField("checkOrderPayed")
	public PayState checkPayed(@GraphQLArgument("outTradeNo") String outTradeNo, @GraphQLArgument("channel") String channel)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		long orderseq = GoodsOrder.parseSeq(outTradeNo);
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() != userseq){
			throw new DataValidateException("invalidate.goods.order.user", String.valueOf(order.getBuyerseq()));
		}
		order.setPaychannel(channel);
		order.setPayid(outTradeNo);
		if("W".equals(channel)){
			WPayService wpay = new WPayService();
			WPayQueryOrderResponse response = wpay.queryOrder(null, outTradeNo);
			String state = response.getTrade_state();
			if(WPayQueryOrderResponse.STATE_SUCCESS.equals(state)){
				order.setPaytime(response.getTime_end());
				daoGoodsOrder.modifyGoodsOrderPayed(order);
			}
			return new PayState(state, response.getTrade_state_desc());
		}else if("A".equals(channel)){
			APayService apay = new APayService();
			APayTradeQueryRequest req = new APayTradeQueryRequest();
			req.setOut_trade_no(outTradeNo);
			APayTradeQueryResponse resp = apay.queryTrade(req);
			if("TRADE_SUCCESS".equals(resp.getTrade_status())){
				order.setPaytime(new Date());
				daoGoodsOrder.modifyGoodsOrderPayed(order);
			}
			String code = resp.getCode();
			return new PayState(code, resp.getMsg());
		}
		throw new DataValidateException("not.support.channel", channel);
	}
	
	@GraphQLField("refuseOrder")
	public boolean refuseOrder(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("reason") String reason)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getSellerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		if(!GoodsOrder.STATUS_PAYED.equals(order.getStatus())){
			throw new DataValidateException("the.order.status.error");
		}
		order.setRefusereason(reason);
		daoGoodsOrder.refuseGoodsOrder(order);
		Refund refund = new Refund(order.getPaychannel(), order.getPayid(), order.getTotal());
		refund.setReason(reason);
		refund.refund();
		return true;
	}
	
	@GraphQLField("closeOrder")
	public boolean closeOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		if(!GoodsOrder.STATUS_UNPAY.equals(order.getStatus())){
			throw new DataValidateException("the.order.status.error");
		}
		daoGoodsOrder.closeGoodsOrder(String.valueOf(order.getOrderseq()));
		return true;
	}
	
	@GraphQLField("removeOrder")
	public boolean removeOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() == userseq){
			order.setHide(order.getHide() | 2);
			daoGoodsOrder.removeTPGoodsOrder(order);
			return true;
		}
		if(order.getSellerseq() == userseq){
			order.setHide(order.getHide() | 1);
			daoGoodsOrder.removeTPGoodsOrder(order);
			return true;
		}
		throw new DataValidateException("the.order.not.belong.to.u", String.valueOf(orderseq));
	}
	
	@GraphQLField("deliveryOrder")
	public boolean deliveryOrder(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("postorder") String postorder, @GraphQLArgument("postcomp") String postcomp)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getSellerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		if(!GoodsOrder.STATUS_PAYED.equals(order.getStatus())&&!GoodsOrder.STATUS_DELIVERY.equals(order.getStatus())){
			throw new DataValidateException("the.order.status.error");
		}
		order.setPostorder(postorder);
		order.setPostcomp(postcomp);
		daoGoodsOrder.deliveryGoodsOrder(order);
		return true;
	}
	
	@GraphQLField("confirmOrder")
	public boolean confirmOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		DAOBalance daoBalance = getDAO().getDAOBalance();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(orderseq);
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		if(!GoodsOrder.STATUS_DELIVERY.equals(order.getStatus())){
			throw new DataValidateException("the.order.status.error");
		}
		daoGoodsOrder.confirmGoodsOrder(order);
		writeBalanceBook(daoBalance, order, daoGoodsRefund.queryGoodsRefundListByOrder(order.getOrderseq()));
		return true;
	}
	
	public void writeBalanceBook(DAOBalance dao, GoodsOrder order, List<GoodsOrderRefund> refunds) throws Exception {
		int goodrate = 100;
		long ownserseq = order.getSellerseq();
		UserBalance bal = getLastUserBalance(dao, ownserseq);
		UserBalance balance = new UserBalance();
		balance.setUserseq(ownserseq);
		balance.setType(UserBalance.TYPE_GOODS_SALE);
		balance.setDescript(order.getTitle());
		balance.setAmount(order.getTotal());
		balance.setBalance(bal.getBalance() + balance.getAmount());
		balance.setSeq(order.getOrderseq());
		balance.setChannel(order.getPaychannel());
		balance.setPayid(order.getPayid());
		balance.setPaytime(order.getPaytime());
		addUserBalance(dao, balance);
		int totalAmount = order.getTotal();
		if(refunds.size() > 0){
			for(GoodsOrderRefund refund : refunds){
				totalAmount -= refund.getAmount();
			}
		}
		BigInteger fee = new BigInteger(String.valueOf(totalAmount));
		String strRate = String.valueOf(goodrate);
		fee = fee.multiply(new BigInteger(strRate)).divide(new BigInteger("10000"));
		balance.setBalseq(balance.getBalseq() + 1);
		balance.setType(UserBalance.TYPE_FEE);
		balance.setDescript("交易手续费");
		balance.setAmount(fee.intValue());
		balance.setBalance(balance.getBalance() - fee.intValue());
		addUserBalance(dao, balance);
		if(refunds.size() > 0){
			for(GoodsOrderRefund refund : refunds){
				balance.setBalseq(balance.getBalseq() + 1);
				balance.setType(UserBalance.TYPE_GOODS_REFUND);
				balance.setDescript("协商退货退款");
				balance.setAmount(refund.getAmount());
				balance.setBalance(balance.getBalance() - balance.getAmount());
				balance.setSeq(refund.getRefundseq());
				balance.setChannel(refund.getPaychannel());
				balance.setPayid(refund.getPayid());
				balance.setPaytime(refund.getRefundtime());
				addUserBalance(dao, balance);
			}
		}
	}
	
	private void addUserBalance(DAOBalance dao, UserBalance balance)throws Exception{
		if(balance.getBalseq() <= 0){
			long seq = getMaxBalanceseq(dao);
			seq++;
			balance.setBalseq(seq);
		}
		if(balance.getBalance() < 0){
			throw new DataValidateException("balance.can.not.less.than.zero", String.valueOf(balance.getBalance()));
		}
		dao.addUserBalance(balance);
	}

	private Long getMaxBalanceseq(DAOBalance dao)throws Exception{
		Long max = dao.getMaxBalanceseq();
		if(max == null|| max == 0){
			max = UserBalance.MIN_USER_BALANCE_SEQ;
		}
		return max;
	}

	private UserBalance getLastUserBalance(DAOBalance dao, long userseq)throws Exception{
		UserBalance bal = dao.getLastUserBalance(userseq);
		if(bal == null){
			bal = new UserBalance();
		}
		return bal;
	}
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private SimpleDateFormat dfAli = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@GraphQLField("withdrawCash")
	public boolean withdrawCash(@GraphQLArgument("amount") int amount)throws Exception{
		long userseq = getEnvironment().getUser().getUserseq();
		DAOBalance app = getDAO().getDAOBalance();
		UserBalance bal = app.getLastUserBalance(userseq);
		if(bal.getBalance() < amount){
			throw new DataValidateException("withdraw.money.more.than.balance", String.valueOf(bal.getBalance()));
		}
		DAOUser bo = getDAO().getDAOUser();
		UserAuth auth = bo.getUserAuth(userseq);
		if(auth.getAlipay() == null||"".equals(auth.getAlipay())){
			throw new DataValidateException("u.not.set.alipay.account");
		}
		String outTradeNo = "WD_" + userseq + "_" + df.format(new Date());
		UserBalance balance = new UserBalance();
		balance.setUserseq(userseq);
		balance.setType(UserBalance.TYPE_WITHDRAW_MONEY);
		balance.setDescript("withdraw cash");
		balance.setAmount(amount);
		balance.setBalance(bal.getBalance() - amount);
		balance.setChannel("A");
		balance.setPayid(outTradeNo);
		addUserBalance(app, balance);		
		APayAccountTransferRequest req = new APayAccountTransferRequest();
		req.setOut_biz_no(outTradeNo);
		req.setPayee_type("ALIPAY_LOGONID");
		req.setPayee_account(auth.getAlipay());
		BigDecimal bi = new BigDecimal(String.valueOf(amount));
		bi = bi.divide(Currency.ONE_HUNDRED);
		req.setAmount(bi.toString());
		req.setPayee_real_name(auth.getUsername());
		req.setPayer_show_name("XXXX TECH");
		req.setRemark("withdraw cash");
		APayService serv = new APayService();
		APayAccountTransferResponse resp = serv.transferAccount(req);
		if(!"10000".equals(resp.getCode())){
			throw new DataValidateException("withdraw.money.error", resp.getMsg(), resp.getSub_code(), resp.getSub_msg());
		}
		balance.setPaytime(dfAli.parse(resp.getPay_date()));
		app.updatePaytime(balance);
		return true;
	}
	
	@GraphQLField("reviewGoods")
	public boolean reviewGoods(@GraphQLArgument("review") ParamGoodsReview review)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(review.getOrderseq());
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		GoodsReview gr = (GoodsReview)review;
		gr.setBuyerseq(userseq);
		gr.setUserseq(order.getSellerseq());
		daoGoodsOrder.addGoodsReview(gr);
		return true;
	}
	
	@GraphQLField("reviewBuyer")
	public boolean reviewBuyer(@GraphQLArgument("review") ParamUserReview review)throws Exception{
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		DAOUser daoUser = getDAO().getDAOUser();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(review.getOrderseq());
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getSellerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		UserReview ur = (UserReview)review;
		ur.setSellerseq(userseq);
		ur.setUserseq(order.getBuyerseq());
		daoUser.addUserReview(ur);
		return true;
	}

}
