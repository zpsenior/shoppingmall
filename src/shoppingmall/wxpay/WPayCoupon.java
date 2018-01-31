package shoppingmall.wxpay;

public class WPayCoupon implements Comparable<WPayCoupon>{
	private Integer no;
	private String id;
	private String type;
	private int fee;
	
	public WPayCoupon(int no){
		this.no = no;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getNo() {
		return no;
	}

	@Override
	public int compareTo(WPayCoupon o) {
		return no.compareTo(o.no);
	}
	
	private String payid;

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}
	
}
