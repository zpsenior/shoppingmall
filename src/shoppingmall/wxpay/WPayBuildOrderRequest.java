package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayBuildOrderRequest extends WPayRequest {
	
	public static String domain = "https://m.szlizhijie.com";

	private String body;
	private String detail;
	private String attach;
	private String out_trade_no;
	private String fee_type;
	private int total_fee;
	private String spbill_create_ip;
	private String time_start;
	private String time_expire;
	private String goods_tag;
	private String trade_type;
	private String limit_pay;
	private String scene_info;
	
	static{
		String str = System.getenv("chain.domain");
		if(str != null){
			domain = str;
		}
	}
	

	public WPayBuildOrderRequest(String signType) {
		super(signType, null);
	}

	@Override
	protected void addFields(TreeMap<String, String> map) {
		map.put("body", body);
		map.put("detail", detail);
		map.put("attach", attach);
		map.put("out_trade_no", out_trade_no);
		map.put("fee_type", fee_type);
		map.put("total_fee", String.valueOf(total_fee));
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("time_start", time_start);
		map.put("time_expire", time_expire);
		map.put("goods_tag", goods_tag);
		map.put("notify_url", domain + "/chain/notifyWPaySuccess.do");
		map.put("trade_type", trade_type);
		map.put("limit_pay", limit_pay);
		map.put("scene_info", scene_info);
		
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return out_trade_no;
	}

	public void setOutTradeNo(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFeeType() {
		return fee_type;
	}

	public void setFeeType(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotalFee() {
		return total_fee;
	}

	public void setTotalFee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbillCreateIp() {
		return spbill_create_ip;
	}

	public void setSpbillCreateIp(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTimeStart() {
		return time_start;
	}

	public void setTimeStart(String time_start) {
		this.time_start = time_start;
	}

	public String getTimeExpire() {
		return time_expire;
	}

	public void setTimeExpire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoodsTag() {
		return goods_tag;
	}

	public void setGoodsTag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getTradeType() {
		return trade_type;
	}

	public void setTradeType(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getLimitPay() {
		return limit_pay;
	}

	public void setLimitPay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getSceneInfo() {
		return scene_info;
	}

	public void setSceneInfo(String scene_info) {
		this.scene_info = scene_info;
	}
	
	
}
