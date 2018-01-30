package shoppingmall.input;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class ScrollQueryGoodsReview extends ScrollQuery {
	private long goodsseq;

	public long getGoodsseq() {
		return goodsseq;
	}

	public void setGoodsseq(long goodsseq) {
		this.goodsseq = goodsseq;
	}
	
	
}
