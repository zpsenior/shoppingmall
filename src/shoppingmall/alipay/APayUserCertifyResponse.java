package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayUserCertifyResponse extends APayResponse {

	public APayUserCertifyResponse() {
		super("zhima_customer_certification_certify_response");
	}
	
	private String biz_no;
	private String passed;
	private String failed_reason;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		biz_no = getText(node, "biz_no");
		passed = getText(node, "passed");
		failed_reason = getText(node, "failed_reason");
	}

	public String getBiz_no() {
		return biz_no;
	}

	public void setBiz_no(String biz_no) {
		this.biz_no = biz_no;
	}

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public String getFailed_reason() {
		return failed_reason;
	}

	public void setFailed_reason(String failed_reason) {
		this.failed_reason = failed_reason;
	}

	
}
