package shoppingmall.alipay;

import java.util.List;
import java.util.TreeMap;

public class APayTradeCreateRequest extends APayRequest {

	private String out_trade_no;
	private String seller_id;
	private Currency total_amount = Currency.ZERO;
	private Currency discountable_amount = Currency.ZERO;
	private Currency undiscountable_amount = Currency.ZERO;
	private String buyer_logon_id;
	private String subject;
	private String body;
	private String buyer_id;
	private List<GoodsDetail> goods_detail;
	private String operator_id;
	private String enable_pay_channels;
	private String store_id;
	private String disable_pay_channels;
	private String terminal_id;

	private ExtendParams extend_params;
	private String timeout_express;
	private RoyaltyInfo royalty_info;
	private String alipay_store_id;
	private SubMerchant sub_merchant;
	private String merchant_order_no;
	
	public APayTradeCreateRequest() {
		super("alipay.trade.create");
	}

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("out_trade_no", out_trade_no);
		bean.put("seller_id", seller_id);
		bean.put("total_amount", total_amount);
		bean.put("discountable_amount", discountable_amount);
		bean.put("undiscountable_amount", undiscountable_amount);
		bean.put("buyer_logon_id", buyer_logon_id);
		bean.put("subject", subject);
		bean.put("body", body);
		bean.put("buyer_id", buyer_id);
		bean.put("goods_detail", goods_detail);
		bean.put("operator_id", operator_id);
		bean.put("enable_pay_channels", enable_pay_channels);
		bean.put("store_id", store_id);
		bean.put("disable_pay_channels", disable_pay_channels);
		bean.put("terminal_id", terminal_id);

		bean.put("extend_params", extend_params);
		bean.put("timeout_express", timeout_express);
		bean.put("royalty_info", royalty_info);
		bean.put("alipay_store_id", alipay_store_id);
		bean.put("sub_merchant", sub_merchant);
		bean.put("merchant_order_no", merchant_order_no);
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public Currency getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Currency total_amount) {
		this.total_amount = total_amount;
	}

	public Currency getDiscountable_amount() {
		return discountable_amount;
	}

	public void setDiscountable_amount(Currency discountable_amount) {
		this.discountable_amount = discountable_amount;
	}

	public Currency getUndiscountable_amount() {
		return undiscountable_amount;
	}

	public void setUndiscountable_amount(Currency undiscountable_amount) {
		this.undiscountable_amount = undiscountable_amount;
	}

	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}

	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public List<GoodsDetail> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<GoodsDetail> goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public void setEnable_pay_channels(String enable_pay_channels) {
		this.enable_pay_channels = enable_pay_channels;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getDisable_pay_channels() {
		return disable_pay_channels;
	}

	public void setDisable_pay_channels(String disable_pay_channels) {
		this.disable_pay_channels = disable_pay_channels;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public ExtendParams getExtend_params() {
		return extend_params;
	}

	public void setExtend_params(ExtendParams extend_params) {
		this.extend_params = extend_params;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public RoyaltyInfo getRoyalty_info() {
		return royalty_info;
	}

	public void setRoyalty_info(RoyaltyInfo royalty_info) {
		this.royalty_info = royalty_info;
	}

	public String getAlipay_store_id() {
		return alipay_store_id;
	}

	public void setAlipay_store_id(String alipay_store_id) {
		this.alipay_store_id = alipay_store_id;
	}

	public SubMerchant getSub_merchant() {
		return sub_merchant;
	}

	public void setSub_merchant(SubMerchant sub_merchant) {
		this.sub_merchant = sub_merchant;
	}

	public String getMerchant_order_no() {
		return merchant_order_no;
	}

	public void setMerchant_order_no(String merchant_order_no) {
		this.merchant_order_no = merchant_order_no;
	}

}
