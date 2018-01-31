package shoppingmall.wxpay;


public class WPayQueryOrderResponse extends WPayNotifyResponse {
	
	public final static String STATE_SUCCESS = "SUCCESS";
	
	private String trade_state;
	private String trade_state_desc;

	@Override
	public void startElement(String key, String value) throws Exception {
		super.startElement(key, value);
		if("trade_state".equals(key)){
			trade_state = value;
			return ;
		}else if("trade_state_desc".equals(key)){
			trade_state_desc = value;
			return ;
		}
	}

	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}
}
