package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayUserVerifyResponse extends APayResponse {

	public APayUserVerifyResponse() {
		super("zhima_credit_antifraud_verify_response");
	}

	private String biz_no;
	private String verify_code;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		biz_no = getText(node, "biz_no");
		verify_code = getText(node, "verify_code");
	}

	public String getBiz_no() {
		return biz_no;
	}

	public String getVerify_code() {
		return verify_code;
	}

}
