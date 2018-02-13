package shoppingmall.app.bo;

import shoppingmall.dao.DAOGoodsOrder;
import shoppingmall.dao.DAOGoodsRefund;
import shoppingmall.exception.DataValidateException;
import shoppingmall.input.ParamGoodsOrderRefund;
import shoppingmall.po.GoodsOrder;
import shoppingmall.po.GoodsOrderRefund;
import shoppingmall.pub.BOBase;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoodsRefund")
public class AppMutationGoodsRefund extends BOBase {

	private long nextRefundseq(DAOGoodsRefund daoGoodsRefund)throws Exception{
		Long key = daoGoodsRefund.getMaxRefundseq();
		if(key == null){
			key = GoodsOrderRefund.MIN_GOODS_ORDER_REFUND_SEQ;
		}
		key++;
		return key;
	}

	@GraphQLField("applyRefund")
	public boolean applyRefund(@GraphQLArgument("refund") ParamGoodsOrderRefund refund)throws Exception{
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		DAOGoodsOrder daoGoodsOrder = getDAO().getDAOGoodsOrder();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrder order = daoGoodsOrder.getGoodsOrder(refund.getOrderseq());
		if(order == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(order.getBuyerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		long refundseq = nextRefundseq(daoGoodsRefund);
		Integer refundno = daoGoodsRefund.getRefundCount(refund);
		if(refundno == null){
			refundno = 0;
		}
		refundno++;
		GoodsOrderRefund ref = (GoodsOrderRefund)refund;
		ref.setBuyerseq(userseq);
		ref.setSellerseq(order.getSellerseq());
		ref.setRefundseq(refundseq);
		ref.setRefundno(refundno);
		daoGoodsRefund.addGoodsRefund(ref);
		return true;
	}
	
	@GraphQLField("cancelRefund")
	public boolean cancelRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrderRefund refund = daoGoodsRefund.getGoodsRefund(refundseq);
		if(refund == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(refund.getBuyerseq() != userseq){
			throw new DataValidateException("goods.refund.not.owner.you");
		}
		if(!GoodsOrderRefund.REFUND_APPLY.equals(refund.getStatus())){
			throw new DataValidateException("the.order.refund.not.apply");
		}
		daoGoodsRefund.cancelGoodsRefund(refund);
		return true;
	}
	
	@GraphQLField("refuseRefund")
	public boolean refuseRefund(@GraphQLArgument("refundseq") long refundseq, @GraphQLArgument("reason") String reason)throws Exception{
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrderRefund refund = daoGoodsRefund.getGoodsRefund(refundseq);
		if(refund == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(refund.getSellerseq() != userseq){
			throw new DataValidateException("goods.refund.not.owner.you");
		}
		if(!GoodsOrderRefund.REFUND_APPLY.equals(refund.getStatus())){
			throw new DataValidateException("the.order.refund.not.apply");
		}
		refund.setRefusereason(reason);
		daoGoodsRefund.refuseGoodsRefund(refund);
		return true;
	}
	

	@GraphQLField("applyArbitration")
	public boolean applyArbitration(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrderRefund refund = daoGoodsRefund.getGoodsRefund(refundseq);
		if(refund == null){
			throw new DataValidateException("invalid.goods.refund");
		}
		if(refund.getBuyerseq() != userseq){
			throw new DataValidateException("goods.order.not.owner.you");
		}
		if(!GoodsOrderRefund.REFUND_APPLY.equals(refund.getStatus())&&!GoodsOrderRefund.REFUND_REFUSE.equals(refund.getStatus())){
			throw new DataValidateException("the.order.refund.not.apply");
		}
		daoGoodsRefund.applyArbitration(refund);
		return true;
	}
	
	@GraphQLField("acceptRefund")
	public boolean acceptRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrderRefund refund = daoGoodsRefund.getGoodsRefund(refundseq);
		if(refund == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(refund.getSellerseq() != userseq){
			throw new DataValidateException("goods.refund.not.owner.you");
		}
		if(!GoodsOrderRefund.REFUND_APPLY.equals(refund.getStatus())){
			throw new DataValidateException("the.order.refund.not.apply");
		}
		daoGoodsRefund.acceptGoodsRefund(refund);
		return true;
	}
	
	@GraphQLField("fillRefundPost")
	public boolean fillRefundPost(@GraphQLArgument("refundseq") long refundseq, @GraphQLArgument("postorder") String postorder, @GraphQLArgument("postcomp") String postcomp)throws Exception{

		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrderRefund refund = daoGoodsRefund.getGoodsRefund(refundseq);
		if(refund == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(refund.getBuyerseq() != userseq){
			throw new DataValidateException("goods.refund.not.owner.you");
		}
		if(!GoodsOrderRefund.REFUND_ACCEPT.equals(refund.getStatus())){
			throw new DataValidateException("the.order.refund.not.accept");
		}
		refund.setPostcompid(postcomp);
		refund.setPostorder(postorder);
		daoGoodsRefund.fillRefundPost(refund);
		return true;
	}
	
	@GraphQLField("confirmRefund")
	public boolean confirmRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		DAOGoodsRefund daoGoodsRefund = getDAO().getDAOGoodsRefund();
		long userseq = getEnvironment().getUser().getUserseq();
		GoodsOrderRefund refund = daoGoodsRefund.getGoodsRefund(refundseq);
		if(refund == null){
			throw new DataValidateException("invalid.goods.order");
		}
		if(refund.getSellerseq() != userseq){
			throw new DataValidateException("goods.refund.not.owner.you");
		}
		if(!GoodsOrderRefund.REFUND_ACCEPT.equals(refund.getStatus())){
			throw new DataValidateException("the.order.refund.not.apply");
		}
		daoGoodsRefund.confirmRefund(refund);
		return true;
	}

}
