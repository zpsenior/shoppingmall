package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayQueryRefundRequest extends WPayRequest {
	
	private String transaction_id;
	private String out_trade_no;
	private String out_refund_no;
	private String refund_id;

	public WPayQueryRefundRequest(String sign) {
		super(sign, null);
	}

	@Override
	protected void addFields(TreeMap<String, String> map) {
		map.put("transaction_id", transaction_id);
		map.put("out_trade_no", out_trade_no);
		map.put("out_refund_no", out_refund_no);
		map.put("refund_id", refund_id);
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

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

}
