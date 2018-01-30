package shoppingmall.app.bo;

import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("Mutation")
public class AppMutation extends BOBase {
	
	public AppMutation(Environment env) {
		super(env);
	}

	@GraphQLField("MUser")
	public AppMutationUser mutationUser()throws Exception{
		return new AppMutationUser(getEnvironment());
	}

	@GraphQLField("MGoods")
	public AppMutationGoods mutationGoods()throws Exception{
		return new AppMutationGoods(getEnvironment());
	}

	@GraphQLField("MGoodsOrder")
	public AppMutationGoodsOrder mutationGoodsOrder()throws Exception{
		return new AppMutationGoodsOrder(getEnvironment());
	}
	@GraphQLField("MGoodsRefund")
	public AppMutationGoodsRefund mutationGoodsRefund()throws Exception{
		return new AppMutationGoodsRefund(getEnvironment());
	}

}
