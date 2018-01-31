package shoppingmall.wxpay;

import java.util.TreeMap;

public class WPayDownloadRequest extends WPayRequest{
	
	public final static String BILL_TYPE_ALL = "ALL";
	public final static String BILL_TYPE_SUCCESS = "SUCCESS";
	public final static String BILL_TYPE_REFUND = "REFUND";
	public final static String BILL_TYPE_RECHARGE_REFUND = "RECHARGE_REFUND";
	
	private String billDate;
	private String billType;
	
	public WPayDownloadRequest(String signType, String tarType){
		super(signType, tarType);
	}
	
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	@Override
	protected void addFields(TreeMap<String, String> map) {	
		map.put("bill_date", billDate);	
		map.put("bill_type", billType);
	}
}
