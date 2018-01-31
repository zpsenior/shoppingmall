package shoppingmall.po;

import java.util.Date;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsOrderRefund extends POBase {
	private long refundseq;
	
	private long orderseq;
	private long goodsseq;
	private long sellerseq;
	private long buyerseq;
	private int refundno;
	
	private String reason;
	private String imgs;
	private String status;
	private int amount;
	private int count;
	
	private String postcompid;
	private String postorder;
	
	private String paychannel;
	private String payid;
	private Date refundtime;
	
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
	public long getRefundseq() {
		return refundseq;
	}
	public void setRefundseq(long refundseq) {
		this.refundseq = refundseq;
	}
	public long getBuyerseq() {
		return buyerseq;
	}
	public void setBuyerseq(long buyerseq) {
		this.buyerseq = buyerseq;
	}
	public long getSellerseq() {
		return sellerseq;
	}
	public void setSellerseq(long sellerseq) {
		this.sellerseq = sellerseq;
	}
	public int getRefundno() {
		return refundno;
	}
	public void setRefundno(int refundno) {
		this.refundno = refundno;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPostcompid() {
		return postcompid;
	}
	public void setPostcompid(String postcompid) {
		this.postcompid = postcompid;
	}
	public String getPostorder() {
		return postorder;
	}
	public void setPostorder(String postorder) {
		this.postorder = postorder;
	}
	public Date getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(Date refundtime) {
		this.refundtime = refundtime;
	}
	public String getPaychannel() {
		return paychannel;
	}
	public void setPaychannel(String paychannel) {
		this.paychannel = paychannel;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	
	public Goods getGoods()throws Exception{
		Goods goods = getEnvironment().getDAOGoods().getGoods(goodsseq);
		goods.bindEnvironment(getEnvironment());
		return goods;
	}
}
