package shoppingmall.wauth;

import java.util.Map;

public class WAuthGetSessionResponse extends WAuthResponse {
	
	private String openid;	//用户唯一标识
	private String session_key;	//会话密钥
	private String unionid;	//用户在开放平台的唯一标识符


	@Override
	protected void setFields(Map<String, String> bean) {
		openid = bean.get("openid");
		session_key = bean.get("session_key");
		unionid = bean.get("unionid");
	}

	public String getOpenid() {
		return openid;
	}


	public String getSession_key() {
		return session_key;
	}


	public String getUnionid() {
		return unionid;
	}

	
}
