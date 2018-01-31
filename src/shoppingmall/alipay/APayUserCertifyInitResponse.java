package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayUserCertifyInitResponse extends APayResponse {

	public APayUserCertifyInitResponse() {
		super("zhima_customer_certification_initialize_response");
	}
	
	private String biz_no;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		biz_no = getText(node, "biz_no");
	}

	public String getBiz_no() {
		return biz_no;
	}

	public void setBiz_no(String biz_no) {
		this.biz_no = biz_no;
	}

	
}
