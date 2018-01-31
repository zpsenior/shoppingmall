package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayUserCreditScoreResponse extends APayResponse {

	public APayUserCreditScoreResponse() {
		super("zhima_credit_score_brief_get_response");
	}
	
	private String biz_no;
	private String is_admittance;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		biz_no = getText(node, "biz_no");
		is_admittance = getText(node, "is_admittance");
	}

	public String getBiz_no() {
		return biz_no;
	}

	public void setBiz_no(String biz_no) {
		this.biz_no = biz_no;
	}

	public String getIs_admittance() {
		return is_admittance;
	}

	public void setIs_admittance(String is_admittance) {
		this.is_admittance = is_admittance;
	}

	
}
