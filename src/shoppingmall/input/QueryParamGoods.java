package shoppingmall.input;

import java.util.Date;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class QueryParamGoods extends QueryParam {
	private String goodsname;
	private int goodsseq;
	private String status;
	private Date begindate;
	private Date enddate;
	private String kind;
	
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public int getGoodsseq() {
		return goodsseq;
	}
	public void setGoodsseq(int goodsseq) {
		this.goodsseq = goodsseq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
}
