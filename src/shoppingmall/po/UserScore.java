package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class UserScore extends POBase {
	
	private long userseq;
	
	private int saleamount;
	private int salecount;
	
	private long praisescore;
	private int praisecount;
	
	private long buyerpraise;
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
	public long getPraisescore() {
		return praisescore;
	}
	public void setPraisescore(long praisescore) {
		this.praisescore = praisescore;
	}
	public int getPraisecount() {
		return praisecount;
	}
	public void setPraisecount(int praisecount) {
		this.praisecount = praisecount;
	}
	public long getBuyerpraise() {
		return buyerpraise;
	}
	public void setBuyerpraise(long buyerpraise) {
		this.buyerpraise = buyerpraise;
	}
	public int getBuyercount() {
		return buyercount;
	}
	public void setBuyercount(int buyercount) {
		this.buyercount = buyercount;
	}
	
	

}
