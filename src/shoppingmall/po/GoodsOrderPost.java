package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;
import java.util.List;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsOrderPost extends POBase {
	private long postseq;
	private long orderseq;
	
	private Date deliverytime;
	
	private String postorder;
	private String postcomp;
	
	private String recipients;
	private String nation;
	private String province;
	private String city;
	private String addr;
	private String mobileno;
	public long getOrderseq() {
		return orderseq;
	}
	public void setOrderseq(long orderseq) {
		this.orderseq = orderseq;
	}
	public long getPostseq() {
		return postseq;
	}
	public void setPostseq(long postseq) {
		this.postseq = postseq;
	}
	public Date getDeliverytime() {
		return deliverytime;
	}
	public void setDeliverytime(Date deliverytime) {
		this.deliverytime = deliverytime;
	}
	public String getPostorder() {
		return postorder;
	}
	public void setPostorder(String postorder) {
		this.postorder = postorder;
	}
	public String getPostcomp() {
		return postcomp;
	}
	public void setPostcomp(String postcomp) {
		this.postcomp = postcomp;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public List<GoodsOrderItem> getItems()throws Exception{
		List<GoodsOrderItem> list = getEnvironment().getDAOGoodsOrder().queryGoodsOrderItemList(orderseq);
		for(int i = list.size() - 1; i >= 0; i--){
			GoodsOrderItem item = list.get(i);
			if(item.getPostseq() != postseq){
				list.remove(item);
			}
		}
		bindEnvironment(list);
		return list;
	}
}
