package shoppingmall.alipay;

import org.codehaus.jackson.JsonNode;

public class APayAccountTransferResponse extends APayResponse {

	public APayAccountTransferResponse() {
		super("alipay_fund_trans_toaccount_transfer_response");
	}
	
	private String out_biz_no;
	private String order_id;
	private String pay_date;

	@Override
	protected void setFields(JsonNode node) throws Exception {
		out_biz_no = getText(node, "out_biz_no");
		order_id = getText(node, "order_id");
		pay_date = getText(node, "pay_date");
	}

	public String getOut_biz_no() {
		return out_biz_no;
	}

	public void setOut_biz_no(String out_biz_no) {
		this.out_biz_no = out_biz_no;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

}
