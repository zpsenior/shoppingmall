package shoppingmall.alipay;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;

public class APayTradePayResponse extends APayResponse {
	
	private String trade_no;
	private String out_trade_no;
	private String buyer_logon_id;
	private Currency total_amount = Currency.ZERO;
	private Currency receipt_amount = Currency.ZERO;
	private Currency buyer_pay_amount = Currency.ZERO;
	private Currency point_amount = Currency.ZERO;
	private Currency invoice_amount = Currency.ZERO;
	private Date gmt_payment;
	private List<TradeFundBill> fund_bill_list;
	private int card_balance;
	private String store_name;
	private String buyer_user_id;
	private List<GoodsDetail> discount_goods_detail;
	private String async_payment_mode;
	private List<VoucherDetail> voucher_detail_list;

	public APayTradePayResponse() {
		super("alipay_trade_pay_response");
	}

	@Override
	protected void setFields(JsonNode node) throws Exception{
		trade_no = getText(node, "trade_no");
		out_trade_no = getText(node, "out_trade_no");
		buyer_logon_id = getText(node, "buyer_logon_id");
		
		total_amount = getCurrency(node, "total_amount");
		receipt_amount = getCurrency(node, "receipt_amount");
		buyer_pay_amount = getCurrency(node, "buyer_pay_amount");
		point_amount = getCurrency(node, "point_amount");
		invoice_amount = getCurrency(node, "invoice_amount");
		
		gmt_payment = getDate(node, "gmt_payment");

		fund_bill_list = getArray(node, "fund_bill_list", TradeFundBill.class);

		card_balance = getInt(node, "card_balance");
		store_name = getText(node, "store_name");
		buyer_user_id = getText(node, "buyer_user_id");
		discount_goods_detail = getArray(node, "discount_goods_detail", GoodsDetail.class);
		async_payment_mode = getText(node, "async_payment_mode");

		voucher_detail_list = getArray(node, "voucher_detail_list", VoucherDetail.class);
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

	public Date getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public List<TradeFundBill> getFund_bill_list() {
		return fund_bill_list;
	}

	public void setFund_bill_list(List<TradeFundBill> fund_bill_list) {
		this.fund_bill_list = fund_bill_list;
	}

	public int getCard_balance() {
		return card_balance;
	}

	public void setCard_balance(int card_balance) {
		this.card_balance = card_balance;
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

	public List<GoodsDetail> getDiscount_goods_detail() {
		return discount_goods_detail;
	}

	public void setDiscount_goods_detail(List<GoodsDetail> discount_goods_detail) {
		this.discount_goods_detail = discount_goods_detail;
	}

	public String getAsync_payment_mode() {
		return async_payment_mode;
	}

	public void setAsync_payment_mode(String async_payment_mode) {
		this.async_payment_mode = async_payment_mode;
	}

	public List<VoucherDetail> getVoucher_detail_list() {
		return voucher_detail_list;
	}

	public void setVoucher_detail_list(List<VoucherDetail> voucher_detail_list) {
		this.voucher_detail_list = voucher_detail_list;
	}

	
}
