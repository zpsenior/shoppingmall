package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayQueryRefundResponse extends WPayResponse {
	
	private String transaction_id;
	private String out_trade_no; 
	private int total_fee;
	private int settlement_total_fee; 
	private String fee_type;
	private int cash_fee;

	private int refund_count;
	private TreeMap<Integer, Refund> refunds = new TreeMap<Integer, Refund>();

	@Override
	public void startElement(String key, String value) throws Exception {
		if("transaction_id".equals(key)){
			transaction_id = value;
			return ;
		}else if("out_trade_no".equals(key)){
			out_trade_no = value;
			return ;
		}else if("total_fee".equals(key)){
			total_fee = Integer.parseInt(value);
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
		}else if("refund_count".equals(key)){
			refund_count = Integer.parseInt(value);
			return ;
		}
		setRefund(key, value);
	}
	
	private Refund getRefund(int no){
		Refund refund = refunds.get(no);
		if(refund == null){
			refund = new Refund(no);
			refunds.put(no, refund);
		}
		return refund;
	}

	private void setRefund(String key, String value){
		int pos = key.indexOf('$');
		if(pos < 0){
			return;
		}
		Refund refund;
		String prop = key.substring(0, pos - 1);
		String suffix = key.substring(pos + 1);
		pos = suffix.indexOf('$');
		if(pos > 0){
			int p = suffix.indexOf('_');
			int rkey = Integer.parseInt(suffix.substring(0, p));
			refund = getRefund(rkey);
			int ckey = Integer.parseInt(suffix.substring(pos + 1));
			WPayCoupon coupon = refund.getCoupon(ckey);
			if(key.startsWith("coupon_refund_id_$")){
				coupon.setId(value);
			}else if(key.startsWith("coupon_refund_fee_$")){
				coupon.setFee(Integer.parseInt(value));
			}
			return;
		}
		Integer no = Integer.parseInt(key);
		refund = getRefund(no);
		try{
			refund.setValue(prop, value);
		}catch(Throwable t){
			
		}
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
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

	public int getRefund_count() {
		return refund_count;
	}
}
