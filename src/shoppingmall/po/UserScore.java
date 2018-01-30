package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class UserScore extends POBase {
	
	private long userseq;
	
	private int saleamount;
	private int salecount;
	
	private int praisescore;
	private int praisecount;
	
	private int buyerpraise;
	private int buyercount;
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public int getSaleamount() {
		return saleamount;
	}
	public void setSaleamount(int saleamount) {
		this.saleamount = saleamount;
	}
	public int getSalecount() {
		return salecount;
	}
	public void setSalecount(int salecount) {
		this.salecount = salecount;
	}
	public int getPraisescore() {
		return praisescore;
	}
	public void setPraisescore(int praisescore) {
		this.praisescore = praisescore;
	}
	public int getPraisecount() {
		return praisecount;
	}
	public void setPraisecount(int praisecount) {
		this.praisecount = praisecount;
	}
	public int getBuyerpraise() {
		return buyerpraise;
	}
	public void setBuyerpraise(int buyerpraise) {
		this.buyerpraise = buyerpraise;
	}
	public int getBuyercount() {
		return buyercount;
	}
	public void setBuyercount(int buyercount) {
		this.buyercount = buyercount;
	}
	
	

}
