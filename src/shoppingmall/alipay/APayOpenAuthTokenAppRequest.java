package shoppingmall.alipay;

import java.util.TreeMap;

public class APayOpenAuthTokenAppRequest extends APayRequest {

	public APayOpenAuthTokenAppRequest() {
		super("alipay.open.auth.token.app");
	}
	
	private String grant_type;
	private String code;

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("grant_type", grant_type);
		bean.put("code", code);
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
