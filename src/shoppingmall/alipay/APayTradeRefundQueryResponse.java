package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayTradeRefundQueryResponse extends APayResponse {
	
	private String trade_no;
	private String out_trade_no;
	private String out_request_no;
	private String refund_reason;
	private Currency total_amount = Currency.ZERO;
	private Currency refund_amount = Currency.ZERO;

	public APayTradeRefundQueryResponse() {
		super("alipay_trade_fastpay_refund_query_response");
	}

	@Override
	protected void setFields(JsonNode node) throws Exception {
		trade_no = getText(node, "trade_no");
		out_trade_no = getText(node, "out_trade_no");
		out_request_no = getText(node, "out_request_no");
		refund_reason = getText(node, "refund_reason");
		total_amount = getCurrency(node, "total_amount");
		refund_amount = getCurrency(node, "refund_amount");
	}

	public String getTrade_no() {
		return trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public String getRefund_reason() {
		return refund_reason;
	}

	public Currency getTotal_amount() {
		return total_amount;
	}

	public Currency getRefund_amount() {
		return refund_amount;
	}

	
}
