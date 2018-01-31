package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayDownloadBillResponse extends APayResponse {

	public APayDownloadBillResponse() {
		super("alipay_data_dataservice_bill_downloadurl_query_response");
	}
	
	private String bill_download_url;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		bill_download_url = getText(node, "bill_download_url");
	}

	public String getBill_download_url() {
		return bill_download_url;
	}

	public void setBill_download_url(String bill_download_url) {
		this.bill_download_url = bill_download_url;
	}

	
}
