package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.List;

import shoppingmall.input.ScrollQueryGoodsRefund;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsOrderItem extends POBase {

	private long orderseq;
	private long goodsseq;
	private String goodsname;
	private long sellerseq;
	private int count;
	private int price;
	private String status;
	
	public GoodsOrderItem(){}
	
	public GoodsOrderItem(Goods goods){
		this.goodsseq = goods.getGoodsseq();
		this.goodsname = goods.getGoodsname();
		this.sellerseq = goods.getSellerseq();
		this.price = goods.getPrice();
	}
	
	public long getOrderseq() {
		return orderseq;
	}
	public void setOrderseq(long orderseq) {
		this.orderseq = orderseq;
	}
	public long getGoodsseq() {
		return goodsseq;
	}
	public void setGoodsseq(long goodsseq) {
		this.goodsseq = goodsseq;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public long getSellerseq() {
		return sellerseq;
	}
	public void setSellerseq(long sellerseq) {
		this.sellerseq = sellerseq;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public User getSeller()throws Exception{
		User user = getDAO().getDAOUser().getUserBySeq(sellerseq);
		return user;
	}

	public Goods getGoods()throws Exception{
		Goods goods = getDAO().getDAOGoods().getGoods(goodsseq);
		return goods;
	}
	
	public List<GoodsOrderRefund> queryGoodsRefundList()throws Exception{
		ScrollQueryGoodsRefund params = new ScrollQueryGoodsRefund();
		params.setOrderseq(orderseq);
		params.setGoodsseq(goodsseq);
		List<GoodsOrderRefund> list = getDAO().getDAOGoodsRefund().queryGoodsRefundList(params);
		return list;
	}
}
