package shoppingmall.alipay;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;

public class APayTradeRefundResponse extends APayResponse {
	
	private String trade_no;
	private String out_trade_no;
	private String buyer_logon_id;
	private String fund_change;
	private Currency refund_fee = Currency.ZERO;
	private Date gmt_refund_pay;
	private List<TradeFundBill> refund_detail_item_list;
	private String store_name;
	private String buyer_user_id;

	public APayTradeRefundResponse() {
		super("alipay_trade_refund_response");
	}

	@Override
	protected void setFields(JsonNode node) throws Exception {

		trade_no = getText(node, "trade_no");
		out_trade_no = getText(node, "out_trade_no");
		buyer_logon_id = getText(node, "buyer_logon_id");
		fund_change = getText(node, "fund_change");
		
		refund_fee = getCurrency(node, "refund_fee");
		gmt_refund_pay = getDate(node, "gmt_refund_pay");
		
		refund_detail_item_list = getArray(node, "refund_detail_item_list", TradeFundBill.class);
		
		store_name = getText(node, "store_name");
		buyer_user_id = getText(node, "buyer_user_id");
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}

	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}

	public String getFund_change() {
		return fund_change;
	}

	public void setFund_change(String fund_change) {
		this.fund_change = fund_change;
	}

	public Currency getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(Currency refund_fee) {
		this.refund_fee = refund_fee;
	}

	public Date getGmt_refund_pay() {
		return gmt_refund_pay;
	}

	public void setGmt_refund_pay(Date gmt_refund_pay) {
		this.gmt_refund_pay = gmt_refund_pay;
	}

	public List<TradeFundBill> getRefund_detail_item_list() {
		return refund_detail_item_list;
	}

	public void setRefund_detail_item_list(
			List<TradeFundBill> refund_detail_item_list) {
		this.refund_detail_item_list = refund_detail_item_list;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getBuyer_user_id() {
		return buyer_user_id;
	}

	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}

	
}
