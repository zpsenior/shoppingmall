package shoppingmall.app.bo;

import shoppingmall.pub.BOBase;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("Mutation")
public class AppMutation extends BOBase {

	@GraphQLField("MUser")
	public AppMutationUser mutationUser()throws Exception{
		return new AppMutationUser();
	}

	@GraphQLField("MGoods")
	public AppMutationGoods mutationGoods()throws Exception{
		return new AppMutationGoods();
	}

	@GraphQLField("MGoodsOrder")
	public AppMutationGoodsOrder mutationGoodsOrder()throws Exception{
		return new AppMutationGoodsOrder();
	}
	@GraphQLField("MGoodsRefund")
	public AppMutationGoodsRefund mutationGoodsRefund()throws Exception{
		return new AppMutationGoodsRefund();
	}

}
