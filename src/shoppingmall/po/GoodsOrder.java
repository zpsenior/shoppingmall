package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;

import shoppingmall.exception.DataValidateException;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class GoodsOrder extends POBase {
	
	public final static String STATUS_UNPAY = "0";
	public final static String STATUS_PAYED = "1";
	public final static String STATUS_DELIVERY = "2";
	public final static String STATUS_CLOSE = "3";
	public final static String STATUS_RECEIPT = "4";
	public final static String STATUS_FINISH = "5";
	public final static String STATUS_DELETE = "D";

	public static final Long MIN_GOODS_ORDER_SEQ = 45566L;
	
	private String title;
	private long orderseq;
	private long buyerseq;
	private long sellerseq;
	private int total;
	private String postkind;
	private int postfee;
	private String paychannel;
	private String prepayid;
	private String payid;
	private Date paytime;
	private String status;
	private int hide;
	
	private String refusereason;
	
	private String recipients;
	private String nation;
	private String province;
	private String city;
	private String addr;
	private String mobileno;
	
	private String postorder;
	private String postcomp;
	
	private Date deliverytime;
	
	public GoodsOrder(){}
	
	public GoodsOrder(UserAddress addr){
		this.recipients = addr.getRecipients();
		this.nation = addr.getNation();
		this.province = addr.getProvince();
		this.city = addr.getCity();
		this.addr = addr.getAddr();
		this.mobileno = addr.getMobileno();
	}
	
	public String getRefusereason() {
		return refusereason;
	}

	public void setRefusereason(String refusereason) {
		this.refusereason = refusereason;
	}

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
	public long getSellerseq() {
		return sellerseq;
	}
	public void setSellerseq(long sellerseq) {
		this.sellerseq = sellerseq;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPostkind() {
		return postkind;
	}
	public void setPostkind(String postkind) {
		this.postkind = postkind;
	}
	public int getPostfee() {
		return postfee;
	}
	public void setPostfee(int postfee) {
		this.postfee = postfee;
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
	public int getHide() {
		return hide;
	}
	public void setHide(int hide) {
		this.hide = hide;
	}
	public String getOutTradeNo(){
		String idx = "GOODS_" + orderseq;
		return idx;
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

	public Date getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(Date deliverytime) {
		this.deliverytime = deliverytime;
	}

	public User getBuyer()throws Exception{
		User user = getDAO().getDAOUser().getUserBySeq(buyerseq);
		user.bindEnvironment(getEnvironment());
		return user;
	}

	public static long parseSeq(String outTradeNo)throws Exception{
		if(!outTradeNo.startsWith("GOODS_")){
			throw new DataValidateException("invalidate.out.trade.no", outTradeNo);
		}
		return Integer.parseInt(outTradeNo.substring(6));
	}
}
