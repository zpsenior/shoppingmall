package shoppingmall.alipay;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;

public class APayTradeQueryResponse extends APayResponse {
	
	private String trade_no;
	private String out_trade_no;
	private String buyer_logon_id;
	private String trade_status;
	private Currency total_amount = Currency.ZERO;
	private Currency receipt_amount = Currency.ZERO;
	private Currency buyer_pay_amount = Currency.ZERO;
	private Currency point_amount = Currency.ZERO;
	private Currency invoice_amount = Currency.ZERO;
	private Date send_pay_date;	
	private String store_id;
	private String terminal_id;
	private List<TradeFundBill>  fund_bill_list;
	private String store_name;
	private String buyer_user_id;

	public APayTradeQueryResponse() {
		super("alipay_trade_query_response");
	}

	@Override
	protected void setFields(JsonNode node) throws Exception{
		trade_no = getText(node, "trade_no");
		out_trade_no = getText(node, "out_trade_no");
		buyer_logon_id = getText(node, "buyer_logon_id");
		trade_status = getText(node, "trade_status");
		total_amount = getCurrency(node, "total_amount");
		receipt_amount = getCurrency(node, "receipt_amount");
		buyer_pay_amount = getCurrency(node, "buyer_pay_amount");
		point_amount = getCurrency(node, "point_amount");
		invoice_amount = getCurrency(node, "invoice_amount");
		send_pay_date = getDate(node, "send_pay_date");	
		store_id = getText(node, "store_id");
		terminal_id = getText(node, "terminal_id");
		fund_bill_list = getArray(node, "fund_bill_list", TradeFundBill.class);
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

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public Currency getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Currency total_amount) {
		this.total_amount = total_amount;
	}

	public Currency getReceipt_amount() {
		return receipt_amount;
	}

	public void setReceipt_amount(Currency receipt_amount) {
		this.receipt_amount = receipt_amount;
	}

	public Currency getBuyer_pay_amount() {
		return buyer_pay_amount;
	}

	public void setBuyer_pay_amount(Currency buyer_pay_amount) {
		this.buyer_pay_amount = buyer_pay_amount;
	}

	public Currency getPoint_amount() {
		return point_amount;
	}

	public void setPoint_amount(Currency point_amount) {
		this.point_amount = point_amount;
	}

	public Currency getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(Currency invoice_amount) {
		this.invoice_amount = invoice_amount;
	}

	public Date getSend_pay_date() {
		return send_pay_date;
	}

	public void setSend_pay_date(Date send_pay_date) {
		this.send_pay_date = send_pay_date;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public List<TradeFundBill> getFund_bill_list() {
		return fund_bill_list;
	}

	public void setFund_bill_list(List<TradeFundBill> fund_bill_list) {
		this.fund_bill_list = fund_bill_list;
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
