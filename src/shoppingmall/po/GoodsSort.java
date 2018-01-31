package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsSort extends POBase {
	private long goodsseq;
	private long ord;
	private int browsecount;
	private int saleamount;
	private int salecount;
	private int praisecount;
	private long praisescore;
	private Date refreshtime;
	private int ontop;
	

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
	public long getGoodsseq() {
		return goodsseq;
	}
	public void setGoodsseq(long goodsseq) {
		this.goodsseq = goodsseq;
	}
	public long getOrd() {
		return ord;
	}
	public void setOrd(long ord) {
		this.ord = ord;
	}
	public int getBrowsecount() {
		return browsecount;
	}
	public void setBrowsecount(int browsecount) {
		this.browsecount = browsecount;
	}
	public Date getRefreshtime() {
		return refreshtime;
	}
	public void setRefreshtime(Date refreshtime) {
		this.refreshtime = refreshtime;
	}
	public int getPraisecount() {
		return praisecount;
	}
	public void setPraisecount(int praisecount) {
		this.praisecount = praisecount;
	}
	public long getPraisescore() {
		return praisescore;
	}
	public void setPraisescore(long praisescore) {
		this.praisescore = praisescore;
	}
	public int getOntop() {
		return ontop;
	}
	public void setOntop(int ontop) {
		this.ontop = ontop;
	}
	
}
