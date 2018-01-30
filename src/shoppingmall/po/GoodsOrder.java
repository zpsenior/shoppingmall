package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsOrder extends POBase {

	private String title;
	private long orderseq;
	private long buyerseq;
	private int total;
	private String paychannel;
	private String prepayid;
	private String payid;
	private String tradeno;
	private Date paytime;
	private String status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getOrderseq() {
		return orderseq;
	}
	public void setOrderseq(long orderseq) {
		this.orderseq = orderseq;
	}
	public long getBuyerseq() {
		return buyerseq;
	}
	public void setBuyerseq(long buyerseq) {
		this.buyerseq = buyerseq;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPaychannel() {
		return paychannel;
	}
	public void setPaychannel(String paychannel) {
		this.paychannel = paychannel;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getTradeno() {
		return tradeno;
	}
	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public User getBuyer()throws Exception{
		User user = getEnvironment().getDAOUser().getUserBySeq(buyerseq);
		user.bindEnvironment(getEnvironment());
		return user;
	}
}
