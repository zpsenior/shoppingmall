package shoppingmall.wxpay;

import java.math.BigDecimal;
import java.util.List;

import shoppingmall.exception.DataValidateException;

public class WPayDayBill implements Comparable<WPayDayBill>{
	
	private int total_count;
	private int total_amount;
	private int refund_count;
	private int refund_amount;
	private int coupon_refund_amount;
	private int total_fee;
	private String tradedate;
	
	private boolean download = false;

	@Override
	public int compareTo(WPayDayBill o) {
		return tradedate.compareTo(o.tradedate);
	}
	
	public WPayDayBill(){}

	
	private int dbl2Int(String value){
		BigDecimal num = new BigDecimal(value);
		num = num.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
		return num.intValue();
	}
	
	public WPayDayBill(String[] values){
		total_count = Integer.parseInt(values[0]);//总交易单数
		total_amount = dbl2Int(values[1]);//总交易额
		refund_amount = dbl2Int(values[2]);//总退款金额
		coupon_refund_amount = dbl2Int(values[3]);//总代金券或立减优惠退款金额
		total_fee = dbl2Int(values[4]);//手续费总金额
		download = true;
	}
	public int getTotal_count() {
		return total_count;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public int getRefund_count() {
		return refund_count;
	}

	public void setRefund_count(int refund_count) {
		this.refund_count = refund_count;
	}

	public int getRefund_amount() {
		return refund_amount;
	}
	public int getCoupon_refund_amount() {
		return coupon_refund_amount;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	
	public String getTradedate() {
		return tradedate;
	}

	public void setTradedate(String tradedate) {
		this.tradedate = tradedate;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	private List<WPayDayBillItem> items = null;
	
	public void setItems(List<WPayDayBillItem> bills) {
		this.items = bills;
	}
	public List<WPayDayBillItem> getItems() {
		return items;
	}

	public void checkItem()throws Exception {
		if(items == null || items.size() <= 0){
			return ;
		}
		if(items.size() != total_count){
			throw new DataValidateException("diff.total.count");
		}
		int amount = 0;
		int refundAmount = 0;
		int couponRefundAmount = 0;
		for(WPayDayBillItem bill : items){
			amount += bill.getTotal_fee();
			refundAmount += bill.getRefund_fee();
			couponRefundAmount += bill.getCoupon_refund_fee();
		}
		if(amount != total_amount){
			throw new DataValidateException("diff.total.amount");
		}
		if(refundAmount != refund_amount){
			throw new DataValidateException("diff.total.refund.amount");
		}
		if(couponRefundAmount != coupon_refund_amount){
			throw new DataValidateException("diff.total.refund.coupon.amount");
		}
	}
	
}
