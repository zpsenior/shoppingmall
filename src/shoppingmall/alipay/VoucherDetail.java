package shoppingmall.alipay;


public class VoucherDetail {
	private String id;
	private String name;
	private String type;
	private Currency amount = Currency.ZERO;
	private int merchant_contribute;
	private int other_contribute;
	private String memo;
	private int purchase_buyer_contribute;
	private int purchase_merchant_contribute;
	private int purchase_ant_contribute;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Currency getAmount() {
		return amount;
	}
	public void setAmount(Currency amount) {
		this.amount = amount;
	}
	public int getMerchant_contribute() {
		return merchant_contribute;
	}
	public void setMerchant_contribute(int merchant_contribute) {
		this.merchant_contribute = merchant_contribute;
	}
	public int getOther_contribute() {
		return other_contribute;
	}
	public void setOther_contribute(int other_contribute) {
		this.other_contribute = other_contribute;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getPurchase_buyer_contribute() {
		return purchase_buyer_contribute;
	}
	public void setPurchase_buyer_contribute(int purchase_buyer_contribute) {
		this.purchase_buyer_contribute = purchase_buyer_contribute;
	}
	public int getPurchase_merchant_contribute() {
		return purchase_merchant_contribute;
	}
	public void setPurchase_merchant_contribute(int purchase_merchant_contribute) {
		this.purchase_merchant_contribute = purchase_merchant_contribute;
	}
	public int getPurchase_ant_contribute() {
		return purchase_ant_contribute;
	}
	public void setPurchase_ant_contribute(int purchase_ant_contribute) {
		this.purchase_ant_contribute = purchase_ant_contribute;
	}
	
	
}
