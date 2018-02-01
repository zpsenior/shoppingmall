package shoppingmall.app.bo;

import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

import java.util.List;

import org.apache.log4j.Logger;

import shoppingmall.cache.Cache;
import shoppingmall.dao.DAOUser;
import shoppingmall.exception.ValidationException;
import shoppingmall.input.ScrollQueryGoods;
import shoppingmall.input.ScrollQueryGoodsOrder;
import shoppingmall.input.ScrollQueryGoodsRefund;
import shoppingmall.input.ScrollQueryGoodsReview;
import shoppingmall.input.ScrollQueryMessage;
import shoppingmall.input.ScrollQueryUserBalance;
import shoppingmall.input.ScrollQueryUserReview;
import shoppingmall.po.Goods;
import shoppingmall.po.GoodsKind;
import shoppingmall.po.GoodsOrder;
import shoppingmall.po.GoodsOrderItem;
import shoppingmall.po.GoodsOrderRefund;
import shoppingmall.po.GoodsReview;
import shoppingmall.po.GoodsShoppingCart;
import shoppingmall.po.User;
import shoppingmall.po.UserAddress;
import shoppingmall.po.UserAuth;
import shoppingmall.po.UserBalance;
import shoppingmall.po.SysMessage;
import shoppingmall.po.UserReview;
import shoppingmall.po.WXUser;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import shoppingmall.wauth.AppConst;
import shoppingmall.wauth.WAuthGetSessionRequest;
import shoppingmall.wauth.WAuthGetSessionResponse;
import shoppingmall.wauth.WAuthService;
import shoppingmall.wxpay.MD5;

@GraphQLObject("Query")
public class AppQuery extends BOBase{
	
	private final Logger log = Logger.getLogger(this.getClass());

	public AppQuery(Environment env) {
		super(env);
	}
	
	@GraphQLField("loginFromWeiXin")
	public WXUser loginFromWeiXin(@GraphQLArgument("code") String code)throws Exception{
		WAuthGetSessionRequest req = new WAuthGetSessionRequest();
		req.setCode(code);
		WAuthService serv = new WAuthService(AppConst.getAppId(), AppConst.getAppSecret());
		WAuthGetSessionResponse resp = serv.getSession(req);
		String openid = resp.getOpenid();
		String unionid = resp.getUnionid();
		log.debug("openid:" + openid);
		log.debug("session_key:" + resp.getSession_key());
		log.debug("unionid:" + unionid);
		WXUser wx = new WXUser();
		wx.setOpenid(openid);
		DAOUser daoUser = getEnvironment().getDAOUser();
		User user = daoUser.getUserByOpenid(openid);
		if(user == null){
			return wx;
		}
		String value = resp.getOpenid() + ":" + resp.getSession_key() + ":" + System.currentTimeMillis();
		String sessionid = MD5.encode(value);
		Cache cache = getEnvironment().getCache();
		cache.setWXSession(sessionid, value);
		wx.setSessionid(sessionid);
		wx.setUser(user);
		return wx;
	}
	
	@GraphQLField("checkSessionInWeiXin")
	public User checkSessionInWeiXin(@GraphQLArgument("sessionid") String sessionid)throws Exception{
		Cache cache = getEnvironment().getCache();
		String value = cache.getWXSession(sessionid);
		if(value == null){
			return null;
		}
		int pos = value.indexOf(':');
		if(pos <= 0){
			return null;
		}
		String openid = value.substring(0, pos);
		DAOUser daoUser = getEnvironment().getDAOUser();
		User user = daoUser.getUserByOpenid(openid);
		if(user == null){
			return null;
		}
		return user;
	}

	@GraphQLField("checkRegister")
	public boolean checkRegister(@GraphQLArgument("nationcode") String nationcode, @GraphQLArgument("mobileno") String mobileno)throws Exception{
		return getEnvironment().getDAOUser().getUserByMobileno(mobileno) == null;
	}

	@GraphQLField("user")
	public User getUser(@GraphQLArgument(value="userseq", notNull=false) Long userseq, @GraphQLArgument(value="nickname", notNull=false) String nickname, @GraphQLArgument(value="mobileno", notNull=false) String mobileno)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		User user;
		if(userseq != null){
			user = daoUser.getUserBySeq(userseq);
		}else if(nickname != null){
			user = daoUser.getUserByNickname(nickname);
		}else if(mobileno != null){
			user = daoUser.getUserByMobileno(mobileno);
		}else{
			throw new ValidationException("all.parameters.are.null");
		}
		bindEnvironment(user);
		return user;
	}
	
	@GraphQLField("userAuth")
	public UserAuth getUserAuth(@GraphQLArgument("userseq") Long userseq)throws Exception{
		UserAuth auth = getEnvironment().getDAOUser().getUserAuth(userseq);
		bindEnvironment(auth);
		return auth;
	}
	
	@GraphQLField("addressList")
	public List<UserAddress> queryUserAddressList(@GraphQLArgument("userseq") long userseq)throws Exception{
		List<UserAddress> list = getEnvironment().getDAOUser().queryUserAddressList(userseq);
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("address")
	public UserAddress getUserAddress(@GraphQLArgument("addrno") long addrno)throws Exception{
		UserAddress addr = getEnvironment().getDAOUser().getUserAddress(addrno);
		bindEnvironment(addr);
		return addr;
	}
	
	@GraphQLField("balance")
	public UserBalance getUserBalance(@GraphQLArgument("balseq") long balseq)throws Exception{
		UserBalance bal = getEnvironment().getDAOBalance().getUserBalance(balseq);
		bindEnvironment(bal);
		return bal;
	}
	
	@GraphQLField("balances")
	public List<UserBalance> queryUserBalanceList(@GraphQLArgument("params") ScrollQueryUserBalance params)throws Exception{
		List<UserBalance> list = getEnvironment().getDAOBalance().queryUserBalanceList(params);
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("message")
	public SysMessage getSysMessage(@GraphQLArgument("msgseq") long msgseq)throws Exception{
		SysMessage msg = getEnvironment().getDAOSysMessage().getSysMessage(msgseq);
		bindEnvironment(msg);
		return msg;
	}
	
	@GraphQLField("messages")
	public List<SysMessage> querySysMessageList(@GraphQLArgument("params") ScrollQueryMessage params)throws Exception{
		List<SysMessage> list = getEnvironment().getDAOSysMessage().querySysMessageList(params);
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("shoppingCarts")
	public List<GoodsShoppingCart> queryGoodsShoppingCartList(@GraphQLArgument("userseq") long userseq)throws Exception{
		List<GoodsShoppingCart> list = getEnvironment().getDAOGoodsOrder().queryGoodsShoppingCartList(userseq);
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("goodsKinds")
	public List<GoodsKind> queryGoodsKindList()throws Exception{
		List<GoodsKind> list = getEnvironment().getDAOGoods().queryGoodsKindList();
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("goodsList")
	public List<Goods> queryGoodsList(@GraphQLArgument("params") ScrollQueryGoods params)throws Exception{
		List<Goods> list = getEnvironment().getDAOGoods().queryGoodsList(params);
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("goods")
	public Goods getGoods(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		Goods goods = getEnvironment().getDAOGoods().getGoods(goodsseq);
		bindEnvironment(goods);
		return goods;
	}

	@GraphQLField("goodsReviews")
	public List<GoodsReview> queryGoodsReviewList(@GraphQLArgument("params") ScrollQueryGoodsReview params)throws Exception{
		List<GoodsReview> list = getEnvironment().getDAOGoods().queryGoodsReviewList(params);
		bindEnvironment(list);
		return list;
	}

	@GraphQLField("userReviews")
	public List<UserReview> queryUserReviewList(@GraphQLArgument("params") ScrollQueryUserReview params)throws Exception{
		List<UserReview> list =  getEnvironment().getDAOUser().queryUserReviewList(params);
		bindEnvironment(list);
		return list;
	}
	
	@GraphQLField("goodsOrders")
	public List<GoodsOrder> queryGoodsOrderList(@GraphQLArgument("params") ScrollQueryGoodsOrder params)throws Exception{
		List<GoodsOrder> list = getEnvironment().getDAOGoodsOrder().queryGoodsOrderList(params);
		bindEnvironment(list);
		return list;
	}

	@GraphQLField("goodsOrder")
	public GoodsOrder getGoodsOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		GoodsOrder order = getEnvironment().getDAOGoodsOrder().getGoodsOrder(orderseq);
		bindEnvironment(order);
		return order;
	}
	
	@GraphQLField("goodsRefunds")
	public List<GoodsOrderRefund> queryGoodsRefundList(@GraphQLArgument("params") ScrollQueryGoodsRefund params)throws Exception{
		List<GoodsOrderRefund> list = getEnvironment().getDAOGoodsRefund().queryGoodsRefundList(params);
		bindEnvironment(list);
		return list;
	}

	@GraphQLField("goodsRefund")
	public GoodsOrderRefund getGoodsRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		GoodsOrderRefund refund = getEnvironment().getDAOGoodsRefund().getGoodsRefund(refundseq);
		bindEnvironment(refund);
		return refund;
	}
	
	@GraphQLField("goodsSales")
	public List<GoodsOrderItem> queryGoodsSaleList(@GraphQLArgument("params") ScrollQueryGoodsOrder params)throws Exception{
		List<GoodsOrderItem> list = getEnvironment().getDAOGoodsOrder().queryGoodsSaleList(params);
		bindEnvironment(list);
		return list;
	}
}
