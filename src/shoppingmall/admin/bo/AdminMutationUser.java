package shoppingmall.admin.bo;

import shoppingmall.pub.BOBase;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MUser")
public class AdminMutationUser extends BOBase {

	@GraphQLField("shieldUser")
	public boolean shieldUser(@GraphQLArgument("userseq") long userseq)throws Exception{
		return false;
	}
	
	@GraphQLField("unshieldUser")
	public boolean unshieldUser(@GraphQLArgument("userseq") long userseq)throws Exception{
		return false;
	}

	@GraphQLField("shieldUserReview")
	public boolean shieldUserReview(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}
	
	@GraphQLField("unshieldUserReview")
	public boolean unshieldUserReview(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}

}
