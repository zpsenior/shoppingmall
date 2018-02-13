package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsShoppingCart extends POBase {

	private long userseq;
	private long goodsseq;
	private int count;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public Goods getGoods()throws Exception{
		Goods goods = getDAO().getDAOGoods().getGoods(goodsseq);
		return goods;
	}
	
}
