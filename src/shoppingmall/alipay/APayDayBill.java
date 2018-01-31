package shoppingmall.alipay;

import java.util.List;

public class APayDayBill implements Comparable<APayDayBill> {
	
	private List<APayDayBillItem> items = null;
	
	private int total_trade_count;
	private Currency total_trade_amount = Currency.ZERO;
	private Currency total_trade_pref_amount = Currency.ZERO;
	private int total_refund_count;
	private Currency total_refund_amount = Currency.ZERO;
	private Currency total_refund_pref_amount = Currency.ZERO;
	private Currency total_alipay_dis_amount;
	private Currency total_store_dis_amount;
	private Currency total_card_cons_amount;
	private Currency total_fee;
	private Currency total_share_profit;
	
	private String tradedate;
	
	private boolean download = false;

	@Override
	public int compareTo(APayDayBill o) {
		return tradedate.compareTo(o.tradedate);
	}
	
	public APayDayBill() {
	}

	public APayDayBill(String[] values) {
		for(int i = 0; i < values.length; i++){
			values[i] = values[i].trim();
		}
		total_trade_count = Integer.parseInt(values[2]);
		total_refund_count = Integer.parseInt(values[3]);
		total_trade_amount = new Currency(values[4]);
		total_trade_pref_amount = new Currency(values[5]);
		total_alipay_dis_amount = new Currency(values[6]);
		total_store_dis_amount = new Currency(values[7]);
		total_card_cons_amount = new Currency(values[8]);
		total_fee = new Currency(values[9]);
		total_share_profit = new Currency(values[10]);
	}

	public List<APayDayBillItem> getItems() {
		return items;
	}

	public void setItems(List<APayDayBillItem> items) {
		this.items = items;
	}

	public int getTotal_trade_count() {
		return total_trade_count;
	}

	public void setTotal_trade_count(int total_trade_count) {
		this.total_trade_count = total_trade_count;
	}

	public Currency getTotal_trade_amount() {
		return total_trade_amount;
	}

	public void setTotal_trade_amount(Currency total_trade_amount) {
		this.total_trade_amount = total_trade_amount;
	}

	public Currency getTotal_trade_pref_amount() {
		return total_trade_pref_amount;
	}

	public void setTotal_trade_pref_amount(Currency total_trade_pref_amount) {
		this.total_trade_pref_amount = total_trade_pref_amount;
	}

	public int getTotal_refund_count() {
		return total_refund_count;
	}

	public void setTotal_refund_count(int total_refund_count) {
		this.total_refund_count = total_refund_count;
	}

	public Currency getTotal_refund_amount() {
		return total_refund_amount;
	}

	public void setTotal_refund_amount(Currency total_refund_amount) {
		this.total_refund_amount = total_refund_amount;
	}

	public Currency getTotal_refund_pref_amount() {
		return total_refund_pref_amount;
	}

	public void setTotal_refund_pref_amount(Currency total_refund_pref_amount) {
		this.total_refund_pref_amount = total_refund_pref_amount;
	}
	
	public Currency getTotal_alipay_dis_amount() {
		return total_alipay_dis_amount;
	}

	public void setTotal_alipay_dis_amount(Currency total_alipay_dis_amount) {
		this.total_alipay_dis_amount = total_alipay_dis_amount;
	}

	public Currency getTotal_store_dis_amount() {
		return total_store_dis_amount;
	}

	public void setTotal_store_dis_amount(Currency total_store_dis_amount) {
		this.total_store_dis_amount = total_store_dis_amount;
	}

	public Currency getTotal_card_cons_amount() {
		return total_card_cons_amount;
	}

	public void setTotal_card_cons_amount(Currency total_card_cons_amount) {
		this.total_card_cons_amount = total_card_cons_amount;
	}

	public Currency getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Currency total_fee) {
		this.total_fee = total_fee;
	}

	public Currency getTotal_share_profit() {
		return total_share_profit;
	}

	public void setTotal_share_profit(Currency total_share_profit) {
		this.total_share_profit = total_share_profit;
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

	public void checkItem()throws Exception {
		
	}
	
	private final static String PREFIX_ROW = "#-----------------------------------------业务汇总列表----------------------------------------";
	private final static String SUFFIX_ROW = "#----------------------------------------业务汇总列表结束-------------------------------------";
	
	public static APayDayBill readDayBill(List<String> lst) throws Exception{
		int len = lst.size();
		boolean start = false;
		for(int i = 1; i < len; i++){
			String line = lst.get(i);
			line = line.trim();
			if(PREFIX_ROW.equals(line)){
				i++;
				start = true;
				continue;
			}
			if(SUFFIX_ROW.equals(line)){
				break;
			}
			if(start){
				if(line.startsWith("合计")){
					return new APayDayBill(line.split(","));
				}
			}
		}
		return null;
	}
}
