package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsReview extends POBase {
	private long orderseq;
	private long goodsseq;
	private long buyerseq;
	private long userseq;
	private int score;
	private String review;
	private String[] imgs;
	private String status;
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
	public long getBuyerseq() {
		return buyerseq;
	}
	public void setBuyerseq(long buyerseq) {
		this.buyerseq = buyerseq;
	}
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String[] getImgs() {
		return imgs;
	}
	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public User getBuyer()throws Exception{
		User user = getEnvironment().getDAOUser().getUserBySeq(buyerseq);
		return user;
	}
}
