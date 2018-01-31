package shoppingmall.alipay;

import java.util.TreeMap;

public class APayTradeAppPayRequest extends APayRequest {
	
	private String body;
	private String subject;
	private String out_trade_no;
	private String timeout_express;
	private Currency total_amount = Currency.ZERO;
	private String product_code = "QUICK_MSECURITY_PAY";
	private String goods_type;

	private String passback_params;
	private String promo_params;
	private String extend_params;
	private String enable_pay_channels;
	private String disable_pay_channels;
	private String store_id;
	
	private String sys_service_provider_id;
	private String needBuyerRealnamed;
	private String TRANS_MEMO;
	private String hb_fq_num;
	private String hb_fq_seller_percent;

	public APayTradeAppPayRequest() {
		super("alipay.trade.app.pay");
	}

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("body", body);
		bean.put("subject", subject);
		bean.put("out_trade_no", out_trade_no);
		bean.put("timeout_express", timeout_express);
		bean.put("total_amount", total_amount.getValue());
		bean.put("product_code", product_code);
		bean.put("goods_type", goods_type);
		bean.put("passback_params", passback_params);
		bean.put("promo_params", promo_params);
		bean.put("extend_params", extend_params);
		bean.put("enable_pay_channels", enable_pay_channels);
		bean.put("disable_pay_channels", disable_pay_channels);
		bean.put("store_id", store_id);
		bean.put("sys_service_provider_id", sys_service_provider_id);
		bean.put("needBuyerRealnamed", needBuyerRealnamed);
		bean.put("TRANS_MEMO", TRANS_MEMO);
		bean.put("hb_fq_num", hb_fq_num);
		bean.put("hb_fq_seller_percent", hb_fq_seller_percent);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if(subject != null && subject.length() > 256){
			subject = subject.substring(0, 256);
		}
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public Currency getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Currency total_amount) {
		this.total_amount = total_amount;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getPassback_params() {
		return passback_params;
	}

	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}

	public String getPromo_params() {
		return promo_params;
	}

	public void setPromo_params(String promo_params) {
		this.promo_params = promo_params;
	}

	public String getExtend_params() {
		return extend_params;
	}

	public void setExtend_params(String extend_params) {
		this.extend_params = extend_params;
	}

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public void setEnable_pay_channels(String enable_pay_channels) {
		this.enable_pay_channels = enable_pay_channels;
	}

	public String getDisable_pay_channels() {
		return disable_pay_channels;
	}

	public void setDisable_pay_channels(String disable_pay_channels) {
		this.disable_pay_channels = disable_pay_channels;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getSys_service_provider_id() {
		return sys_service_provider_id;
	}

	public void setSys_service_provider_id(String sys_service_provider_id) {
		this.sys_service_provider_id = sys_service_provider_id;
	}

	public String getNeedBuyerRealnamed() {
		return needBuyerRealnamed;
	}

	public void setNeedBuyerRealnamed(String needBuyerRealnamed) {
		this.needBuyerRealnamed = needBuyerRealnamed;
	}

	public String getTRANS_MEMO() {
		return TRANS_MEMO;
	}

	public void setTRANS_MEMO(String tRANS_MEMO) {
		TRANS_MEMO = tRANS_MEMO;
	}

	public String getHb_fq_num() {
		return hb_fq_num;
	}

	public void setHb_fq_num(String hb_fq_num) {
		this.hb_fq_num = hb_fq_num;
	}

	public String getHb_fq_seller_percent() {
		return hb_fq_seller_percent;
	}

	public void setHb_fq_seller_percent(String hb_fq_seller_percent) {
		this.hb_fq_seller_percent = hb_fq_seller_percent;
	}

	
	
}
