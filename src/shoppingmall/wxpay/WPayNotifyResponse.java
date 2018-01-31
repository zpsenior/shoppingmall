package shoppingmall.wxpay;

import java.util.Date;
import java.util.TreeMap;

public class WPayNotifyResponse extends WPayResponse {
	private String transaction_id;
	private String out_trade_no;
	private String openid;
	private String is_subscribe;
	private String trade_type;
	private String bank_type;
	private int total_fee;
	private String fee_type;
	private int cash_fee;
	private String cash_fee_type;
	private int coupon_fee;
	private int coupon_count;
	private TreeMap<Integer, WPayCoupon> coupons = new TreeMap<Integer, WPayCoupon>();
	private String attach;
	private Date time_end;
	
	private String settlement_total_fee;
	
	public void startElement(String key, String value)throws Exception{
		if("openid".equals(key)){
			openid = value;
			return ;
		}else if("is_subscribe".equals(key)){
			is_subscribe = value;
			return ;
		}else if("trade_type".equals(key)){
			trade_type = value;
			return ;
		}else if("bank_type".equals(key)){
			bank_type = value;
			return ;
		}else if("total_fee".equals(key)){
			total_fee = parseInt(value);
			return ;
		}else if("fee_type".equals(key)){
			fee_type = value;
			return ;
		}else if("cash_fee".equals(key)){
			cash_fee = parseInt(value);
			return ;
		}else if("cash_fee_type".equals(key)){
			cash_fee_type = value;
			return ;
		}else if("coupon_fee".equals(key)){
			coupon_fee = parseInt(value);
			return ;
		}else if("coupon_count".equals(key)){
			coupon_count = parseInt(value);
			return ;
		}else if("transaction_id".equals(key)){
			transaction_id = value;
			return ;
		}else if("out_trade_no".equals(key)){
			out_trade_no = value;
			return ;
		}else if("attach".equals(key)){
			attach = value;
			return ;
		}else if("time_end".equals(key)){
			time_end = parseDate(value);
			return ;
		}else if(key.startsWith("coupon_id_$")){
			WPayCoupon coupon = getCoupon(key, 11);
			coupon.setId(value);
			return ;
		}else if(key.startsWith("coupon_fee_$")){
			WPayCoupon coupon = getCoupon(key, 12);
			coupon.setFee(Integer.parseInt(value));
			return ;
		}else if(key.startsWith("coupon_type_$")){
			WPayCoupon coupon = getCoupon(key, 13);
			coupon.setType(value);
			return ;
		}else if("settlement_total_fee".equals(key)){
			settlement_total_fee = value;
			return ;
		}
		
	}
	
	private WPayCoupon getCoupon(String key, int len) {
		Integer no = Integer.parseInt(key.substring(len));
		WPayCoupon coupon = coupons.get(no);
		if(coupon == null){
			coupon = new WPayCoupon(no);
			coupons.put(no, coupon);
		}
		return coupon;
	}

	public WPayNotifyResponse(){}
	
	public String getPayid(){
		return out_trade_no;
	}
	
	public  String getOpenid() {
		return openid;
	}
	public  String getIs_subscribe() {
		return is_subscribe;
	}
	public  String getTrade_type() {
		return trade_type;
	}
	public  String getBank_type() {
		return bank_type;
	}
	public  int getTotal_fee() {
		return total_fee;
	}
	public  String getFee_type() {
		return fee_type;
	}
	public  int getCash_fee() {
		return cash_fee;
	}
	public  String getCash_fee_type() {
		return cash_fee_type;
	}
	public  int getCoupon_fee() {
		return coupon_fee;
	}
	public  int getCoupon_count() {
		return coupon_count;
	}
	public WPayCoupon[] getCoupons() {
		return coupons.values().toArray(new WPayCoupon[coupons.size()]);
	}
	public  String getAttach() {
		return attach;
	}
	public  Date getTime_end() {
		return time_end;
	}
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	
}
