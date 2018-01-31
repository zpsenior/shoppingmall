package shoppingmall.alipay;

import java.util.TreeMap;

public class APayTradeQueryRequest extends APayRequest {
	
	private String out_trade_no;
	
	private String trade_no;

	public APayTradeQueryRequest() {
		super("alipay.trade.query");
	}

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("out_trade_no", out_trade_no);
		bean.put("trade_no", trade_no);
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
