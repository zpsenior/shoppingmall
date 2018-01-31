package shoppingmall.alipay;

import java.util.List;
import java.util.TreeMap;

public class APayTradePayRequest extends APayRequest {
	
	private String out_trade_no;
	
	private String scene;
	private String auth_code;
	private String product_code;
	private String subject;
	
	private String buyer_id;
	private String seller_id;
	private Currency total_amount = Currency.ZERO;
	private Currency discountable_amount = Currency.ZERO;
	private Currency undiscountable_amount = Currency.ZERO;
	private String body;
	private List<GoodsDetail> goods_detail;
	private String operator_id;
	private String store_id;
	private String terminal_id;
	private String alipay_store_id;
	private ExtendParams extend_params;
	private String timeout_express;
	private RoyaltyInfo royalty_info;
	private SubMerchant sub_merchant;
	private String disable_pay_channels;
	private ExtUserInfo ext_user_info;
	
	public APayTradePayRequest() {
		super("alipay.trade.pay");
	}

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("out_trade_no", out_trade_no);
		
		bean.put("scene", scene);
		bean.put("auth_code", auth_code);
		bean.put("product_code", product_code);
		bean.put("subject", subject);

		bean.put("buyer_id", buyer_id);
		bean.put("seller_id", seller_id);
		bean.put("total_amount", total_amount);
		bean.put("discountable_amount", discountable_amount);
		bean.put("undiscountable_amount", undiscountable_amount);
		bean.put("body", body);
		bean.put("goods_detail", goods_detail);
		bean.put("operator_id", operator_id);
		bean.put("store_id", store_id);
		bean.put("terminal_id", terminal_id);
		bean.put("alipay_store_id", alipay_store_id);

		bean.put("extend_params", extend_params);
		bean.put("timeout_express", timeout_express);
		bean.put("royalty_info", royalty_info);
		bean.put("sub_merchant", sub_merchant);
		bean.put("disable_pay_channels", disable_pay_channels);
		bean.put("ext_user_info", ext_user_info);
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

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public ExtUserInfo getExt_user_info() {
		return ext_user_info;
	}

	public void setExt_user_info(ExtUserInfo ext_user_info) {
		this.ext_user_info = ext_user_info;
	}

}
