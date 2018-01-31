package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayOpenAuthTokenAppResponse extends APayResponse {

	public APayOpenAuthTokenAppResponse() {
		super("alipay_open_auth_token_app_response");
	}
	
	private String app_auth_token;
	private String user_id;
	private String auth_app_id;
	private long expires_in;
	private long re_expires_in;
	private String app_refresh_token;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		app_auth_token = getText(node, "app_auth_token");
		user_id = getText(node, "user_id");
		auth_app_id = getText(node, "auth_app_id");
		expires_in = getLong(node, "expires_in");
		re_expires_in = getLong(node, "re_expires_in");
		app_refresh_token = getText(node, "app_refresh_token");
	}

	public String getApp_auth_token() {
		return app_auth_token;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getAuth_app_id() {
		return auth_app_id;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public long getRe_expires_in() {
		return re_expires_in;
	}

	public String getApp_refresh_token() {
		return app_refresh_token;
	}

	
}
