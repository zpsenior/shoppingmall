package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayUserWatchlistResponse extends APayResponse {

	public APayUserWatchlistResponse() {
		super("zhima_credit_watchlist_brief_get_response");
	}
	
	private String biz_no;
	private String level;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		biz_no = getText(node, "biz_no");
		level = getText(node, "level");
	}

	public String getBiz_no() {
		return biz_no;
	}

	public void setBiz_no(String biz_no) {
		this.biz_no = biz_no;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
