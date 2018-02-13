package shoppingmall.admin.bo;

import shoppingmall.pub.BOBase;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoods")
public class AdminMutationGoods extends BOBase {

	@GraphQLField("shieldGoods")
	public boolean shieldGoods(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}
	
	@GraphQLField("unshieldGoods")
	public boolean unshieldGoods(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}

	@GraphQLField("shieldGoodsReview")
	public boolean shieldGoodsReview(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}
	
	@GraphQLField("unshieldGoodsReview")
	public boolean unshieldGoodsReview(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		return false;
	}

}
