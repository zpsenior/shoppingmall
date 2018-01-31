package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayRefundRequest extends WPayRequest {
	
	private String transaction_id;
	private String out_trade_no;
	private String out_refund_no;
	private int total_fee;
	private int refund_fee;
	private String refund_fee_type;
	private String refund_desc;
	private String refund_account;

	public WPayRefundRequest(String sign) {
		super(sign, null);
	}

	@Override
	protected void addFields(TreeMap<String, String> map) {
		map.put("transaction_id", transaction_id);
		map.put("out_trade_no", out_trade_no);
		map.put("out_refund_no", out_refund_no);
		map.put("total_fee", String.valueOf(total_fee));
		map.put("refund_fee", String.valueOf(refund_fee));
		map.put("refund_fee_type", refund_fee_type);
		map.put("refund_desc", refund_desc);
		map.put("refund_account", refund_account);
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getRefund_desc() {
		return refund_desc;
	}

	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
	}

	public String getRefund_account() {
		return refund_account;
	}

	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}

}
