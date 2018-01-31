package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayUserCertifyQueryResponse extends APayResponse {

	public APayUserCertifyQueryResponse() {
		super("zhima_customer_certification_query_response");
	}
	
	private String passed;
	private String failed_reason;
	private String channel_statuses;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		passed = getText(node, "passed");
		failed_reason = getText(node, "failed_reason");
		channel_statuses = getText(node, "channel_statuses");
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

	public String getChannel_statuses() {
		return channel_statuses;
	}

	public void setChannel_statuses(String channel_statuses) {
		this.channel_statuses = channel_statuses;
	}

	
}
