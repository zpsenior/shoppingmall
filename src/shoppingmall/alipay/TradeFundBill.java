package shoppingmall.alipay;


public class TradeFundBill {
	private String fund_channel;
	private Currency amount = Currency.ZERO;
	private String real_amount;
	
	public String getFund_channel() {
		return fund_channel;
	}
	public void setFund_channel(String fund_channel) {
		this.fund_channel = fund_channel;
	}
	public Currency getAmount() {
		return amount;
	}
	public void setAmount(Currency amount) {
		this.amount = amount;
	}
	public String getReal_amount() {
		return real_amount;
	}
	public void setReal_amount(String real_amount) {
		this.real_amount = real_amount;
	}
	
}
