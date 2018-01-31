package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayTradeCreateResponse extends APayResponse {
	
	public APayTradeCreateResponse() {
		super("alipay_trade_create_response");
	}

	private String out_trade_no;
	private String trade_no;
	
	@Override
	protected void setFields(JsonNode node) {
		out_trade_no = getText(node, "out_trade_no");
		trade_no = getText(node, "trade_no");
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
	
	
}
