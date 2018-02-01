package shoppingmall.wauth;

import java.util.TreeMap;

public class WAuthGetSessionRequest extends WAuthRequest {
	
	private String code;


	@Override
	protected void addFields(TreeMap<String, String> bean) {
		bean.put("js_code", code);
		bean.put("grant_type", "authorization_code");
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	
	
}
