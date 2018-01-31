package shoppingmall.alipay;

import java.util.TreeMap;

public class APayTradeCloseRequest extends APayRequest {
	
	private String trade_no;
	
	private String out_trade_no;
	
	private String operator_id;

	public APayTradeCloseRequest() {
		super("alipay.trade.close");
	}

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("trade_no", trade_no);
		bean.put("out_trade_no", out_trade_no);
		bean.put("operator_id", operator_id);
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

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	
}
