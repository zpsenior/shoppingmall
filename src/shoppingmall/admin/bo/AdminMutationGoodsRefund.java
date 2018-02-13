package shoppingmall.admin.bo;

import shoppingmall.pub.BOBase;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoodsRefund")
public class AdminMutationGoodsRefund extends BOBase {

	@GraphQLField("refundArbitration")
	public boolean refundArbitration(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}

}
