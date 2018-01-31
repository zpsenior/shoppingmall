package shoppingmall.alipay;

import java.util.TreeMap;

public class APayTradeRefundRequest extends APayRequest {
	
	private String out_trade_no;
	private String trade_no;
	private Currency refund_amount = Currency.ZERO;
	private String refund_reason;
	private String out_request_no;
	private String operator_id;
	private String store_id;
	private String terminal_id;

	public APayTradeRefundRequest() {
		super("alipay.trade.refund");
	}

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("out_trade_no", out_trade_no);
		bean.put("trade_no", trade_no);
		bean.put("refund_amount", refund_amount);
		bean.put("refund_reason", refund_reason);
		bean.put("out_request_no", out_request_no);
		bean.put("operator_id", operator_id);
		bean.put("store_id", store_id);
		bean.put("terminal_id", terminal_id);
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Currency getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(Currency refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getRefund_reason() {
		return refund_reason;
	}

	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	
}
