package shoppingmall.wxpay;

import java.lang.reflect.Field;
import java.util.TreeMap;

public class Refund implements Comparable<Refund> {
	
	private Integer no;
	
	private String out_refund_no; 
	private String refund_id;
	private String refund_channel;
	private Integer refund_fee;
	private Integer settlement_refund_fee;

	private String coupon_type;
	private Integer coupon_refund_fee;
	private Integer coupon_refund_count;
	private TreeMap<Integer, WPayCoupon> coupons = new TreeMap<Integer, WPayCoupon>();
	private String refund_status;
	private String refund_account;
	private String refund_recv_account;
	private String refund_success_time;
	
	public Refund(int no){
		this.no = no;
	}

	@Override
	public int compareTo(Refund o) {
		return no.compareTo(o.no);
	}
	
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public String getRefund_channel() {
		return refund_channel;
	}
	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}
	public Integer getSettlement_refund_fee() {
		return settlement_refund_fee;
	}
	public void setSettlement_refund_fee(Integer settlement_refund_fee) {
		this.settlement_refund_fee = settlement_refund_fee;
	}
	public String getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}
	public Integer getCoupon_refund_fee() {
		return coupon_refund_fee;
	}
	public void setCoupon_refund_fee(Integer coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}
	public Integer getCoupon_refund_count() {
		return coupon_refund_count;
	}
	public void setCoupon_refund_count(Integer coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public String getRefund_account() {
		return refund_account;
	}
	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}
	public String getRefund_recv_account() {
		return refund_recv_account;
	}
	public void setRefund_recv_account(String refund_recv_account) {
		this.refund_recv_account = refund_recv_account;
	}
	public String getRefund_success_time() {
		return refund_success_time;
	}
	public void setRefund_success_time(String refund_success_time) {
		this.refund_success_time = refund_success_time;
	}
	public WPayCoupon[] getCoupons() {
		return coupons.values().toArray(new WPayCoupon[coupons.size()]);
	}

	public WPayCoupon getCoupon(int no) {
		WPayCoupon coupon = coupons.get(no);
		if(coupon == null){
			coupon = new WPayCoupon(no);
			coupons.put(no, coupon);
		}
		return coupon;
	}
	
	@SuppressWarnings("rawtypes")
	private Class cls = this.getClass();

	public void setValue(String prop, String value)throws Exception {
		Field fld = cls.getField(prop);
		if(fld.getType().getName().equals("java.lang.Integer")){
			Integer v = Integer.parseInt(value);
			fld.set(this, v);
			return;
		}
		fld.set(this, value);
	}
}
