package shoppingmall.wauth;

import java.util.TreeMap;

public class WAuthRefreshTokenRequest extends WAuthRequest {
	
	private String refresh_token;
	private String grant_type;

	@Override
	protected void addFields(TreeMap<String, String> bean) {
		bean.put("refresh_token", refresh_token);
		bean.put("grant_type", grant_type);
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	
}
