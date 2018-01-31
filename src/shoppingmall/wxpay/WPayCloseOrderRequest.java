package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayCloseOrderRequest extends WPayRequest {
	
	private String out_trade_no;

	public WPayCloseOrderRequest(String signType) {
		super(signType, null);
	}

	@Override
	protected void addFields(TreeMap<String, String> map) {
		map.put("out_trade_no", out_trade_no);
	}

	public String getOutTradeNo() {
		return out_trade_no;
	}

	public void setOutTradeNo(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

}
