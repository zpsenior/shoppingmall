package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayCallPayRequest extends WPayRequest {

	public WPayCallPayRequest(String sign) {
		super(sign, null);
		timestamp = String.valueOf(System.currentTimeMillis()/1000);
	}
	
	private String prepayid;
	private String pkg = "Sign=WXPay";
	private String timestamp;

	@Override
	protected void addFields(TreeMap<String, String> map) {
		map.put("prepayid", prepayid);
		map.remove("mch_id");
		map.remove("device_info");
		String noncestr = map.remove("nonce_str");
		map.put("noncestr", noncestr);
		map.put("partnerid", getMchId());
		map.put("package", pkg);
		map.put("timestamp", timestamp);
	}

	public static String getAppid() {
		return getAppId();
	}

	public String getPartnerid() {
		return getMchId();
	}

	public String getPackage() {
		return pkg;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

}
