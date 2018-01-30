package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsFavorite extends POBase {
	
	private long goodsseq;
	private long userseq;
	
	public long getGoodsseq() {
		return goodsseq;
	}
	public void setGoodsseq(long goodsseq) {
		this.goodsseq = goodsseq;
	}
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	
	
	public Goods getGoods()throws Exception{
		Goods goods = getEnvironment().getDAOGoods().getGoods(goodsseq);
		goods.bindEnvironment(getEnvironment());
		return goods;
	}
	
}
