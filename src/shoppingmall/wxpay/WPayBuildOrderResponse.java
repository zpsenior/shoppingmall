package shoppingmall.wxpay;

public class WPayBuildOrderResponse extends WPayResponse {
	
	private String prepay_id;
	
	private String trade_type;
	
	private String mweb_url;
	
	@Override
	public void startElement(String key, String value) throws Exception{
		if("trade_type".equals(key)){
			trade_type = value;
			return ;
		}else if("prepay_id".equals(key)){
			prepay_id = value;
			return ;
		}else if("mweb_url".equals(key)){
			mweb_url = value;
			return ;
		}
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getMweb_url() {
		return mweb_url;
	}

	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}
	
	
}
