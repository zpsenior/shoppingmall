package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayTradeCloseResponse extends APayResponse {
	
	private String trade_no;
	
	private String out_trade_no;

	public APayTradeCloseResponse() {
		super("alipay_trade_close_response");
	}

	@Override
	protected void setFields(JsonNode node) throws Exception {
		out_trade_no = getText(node, "out_trade_no");
		trade_no = getText(node, "trade_no");
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

}
