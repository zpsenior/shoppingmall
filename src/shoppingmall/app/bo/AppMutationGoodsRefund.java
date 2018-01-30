package shoppingmall.app.bo;

import shoppingmall.input.ParamGoodsOrderRefund;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoodsRefund")
public class AppMutationGoodsRefund extends BOBase {
	
	public AppMutationGoodsRefund(Environment env) {
		super(env);
	}

	@GraphQLField("applyRefund")
	public boolean applyRefund(@GraphQLArgument("refund") ParamGoodsOrderRefund refund)throws Exception{
		return false;
	}
	
	@GraphQLField("cancelRefund")
	public boolean cancelRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		return false;
	}
	
	@GraphQLField("refuseRefund")
	public boolean refuseRefund(@GraphQLArgument("refundseq") long refundseq, @GraphQLArgument("reason") String reason)throws Exception{
		return false;
	}
	
	@GraphQLField("acceptRefund")
	public boolean acceptRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		return false;
	}
	
	@GraphQLField("fillRefundPost")
	public boolean fillRefundPost(@GraphQLArgument("refundseq") long refundseq, @GraphQLArgument("postorder") String postorder, @GraphQLArgument("postcomp") String postcomp)throws Exception{
		return false;
	}
	
	@GraphQLField("confirmRefund")
	public boolean confirmRefund(@GraphQLArgument("refundseq") long refundseq)throws Exception{
		return false;
	}

}
