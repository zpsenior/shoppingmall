package shoppingmall.wauth;

import java.util.TreeMap;

public class WAuthAccessTokenRequest extends WAuthRequest {
	
	private String code;
	private String grant_type;

	@Override
	protected void addFields(TreeMap<String, String> bean) {
		bean.put("code", code);
		bean.put("grant_type", grant_type);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	
}
