package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class UserBalance extends POBase {
	
	public final static String TYPE_FEE = "1";
	public final static String TYPE_WITHDRAW_MONEY = "2";
	public final static String TYPE_GOODS_SALE = "3";
	public final static String TYPE_GOODS_REFUND = "4";
	
	public static final Long MIN_USER_BALANCE_SEQ = 2000L;

	private long balseq = -1;
	private long userseq;
	private String type;
	private long seq;
	private String descript;
	private int amount;
	private int balance;
	private String channel;
	private String payid;
	private Date paytime;
	
	public long getBalseq() {
		return balseq;
	}
	public void setBalseq(long balseq) {
		this.balseq = balseq;
	}
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	
}
