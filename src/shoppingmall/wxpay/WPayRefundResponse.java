package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayRefundResponse extends WPayResponse {
	
	private String transaction_id;
	private String out_trade_no;  
	private String out_refund_no; 
	private String refund_id;
	private int refund_fee;
	private int settlement_refund_fee;
	private int total_fee;
	private int settlement_total_fee; 
	private String fee_type;
	private int cash_fee;
	private String cash_fee_type;
	private int cash_refund_fee;
	private int coupon_refund_fee;
	private int coupon_refund_count;
	private TreeMap<Integer, WPayCoupon> coupons = new TreeMap<Integer, WPayCoupon>();

	@Override
	public void startElement(String key, String value) throws Exception {
		if("transaction_id".equals(key)){
			transaction_id = value;
			return ;
		}else if("out_trade_no".equals(key)){
			out_trade_no = value;
			return ;
		}else if("out_refund_no".equals(key)){
			out_refund_no = value;
			return ;
		}else if("refund_id".equals(key)){
			refund_id = value;
			return ;
		}else if("refund_fee".equals(key)){
			refund_fee = Integer.parseInt(value);
			return ;
		}else if("settlement_total_fee".equals(key)){
			settlement_total_fee = Integer.parseInt(value);
			return ;
		}else if("fee_type".equals(key)){
			fee_type = value;
			return ;
		}else if("cash_fee".equals(key)){
			cash_fee = Integer.parseInt(value);
			return ;
		}else if("cash_fee_type".equals(key)){
			cash_fee_type = value;
			return ;
		}else if("cash_refund_fee".equals(key)){
			cash_refund_fee = Integer.parseInt(value);
			return ;
		}else if("coupon_refund_fee".equals(key)){
			coupon_refund_fee = Integer.parseInt(value);
			return ;
		}else if("coupon_refund_count".equals(key)){
			coupon_refund_count = Integer.parseInt(value);
			return ;
		}else if(key.startsWith("coupon_refund_id_$")){
			WPayCoupon coupon = getCoupon(key, 18);
			coupon.setId(value);
			return ;
		}else if(key.startsWith("coupon_refund_fee_$")){
			WPayCoupon coupon = getCoupon(key, 19);
			coupon.setFee(Integer.parseInt(value));
			return ;
		}else if(key.startsWith("coupon_type_$")){
			WPayCoupon coupon = getCoupon(key, 13);
			coupon.setType(value);
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
	
	public String getPayid(){
		if(transaction_id != null){
			return transaction_id;
		}
		return out_trade_no;
	}
	
	public String getWeixin(){
		if(transaction_id != null){
			return "1";
		}
		return "0";
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public int getSettlement_refund_fee() {
		return settlement_refund_fee;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public int getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public int getCash_fee() {
		return cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public int getCash_refund_fee() {
		return cash_refund_fee;
	}

	public int getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public int getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public WPayCoupon[] getCoupons() {
		return coupons.values().toArray(new WPayCoupon[coupons.size()]);
	}

}
