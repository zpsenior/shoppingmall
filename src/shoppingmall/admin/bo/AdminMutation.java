package shoppingmall.admin.bo;

import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.BOBase;

@GraphQLObject("Mutation")
public class AdminMutation extends BOBase {

	@GraphQLField("MUser")
	public AdminMutationUser mutationUser()throws Exception{
		return new AdminMutationUser();
	}

	@GraphQLField("MGoods")
	public AdminMutationGoods mutationGoods()throws Exception{
		return new AdminMutationGoods();
	}

	@GraphQLField("MMessage")
	public AdminMutationSysMessage mutationGoodsOrder()throws Exception{
		return new AdminMutationSysMessage();
	}
	@GraphQLField("MGoodsRefund")
	public AdminMutationGoodsRefund mutationGoodsRefund()throws Exception{
		return new AdminMutationGoodsRefund();
	}

}
