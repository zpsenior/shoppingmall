package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayQueryOrderRequest extends WPayRequest {


	private String out_trade_no;
	

	public WPayQueryOrderRequest(String signType) {
		super(signType, null);

	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.out_trade_no = outTradeNo;
	}

	@Override
	protected void addFields(TreeMap<String, String> map) {
		map.put("out_trade_no", out_trade_no);
	}
}
