package shoppingmall.admin.bo;

import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;

@GraphQLObject("Mutation")
public class AdminMutation extends BOBase {

	public AdminMutation(Environment env) {
		super(env);
	}

	@GraphQLField("MUser")
	public AdminMutationUser mutationUser()throws Exception{
		return new AdminMutationUser(getEnvironment());
	}

	@GraphQLField("MGoods")
	public AdminMutationGoods mutationGoods()throws Exception{
		return new AdminMutationGoods(getEnvironment());
	}

	@GraphQLField("MMessage")
	public AdminMutationSysMessage mutationGoodsOrder()throws Exception{
		return new AdminMutationSysMessage(getEnvironment());
	}
	@GraphQLField("MGoodsRefund")
	public AdminMutationGoodsRefund mutationGoodsRefund()throws Exception{
		return new AdminMutationGoodsRefund(getEnvironment());
	}

}
