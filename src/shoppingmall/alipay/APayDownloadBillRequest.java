package shoppingmall.alipay;

import java.util.TreeMap;

public class APayDownloadBillRequest extends APayRequest {

	public APayDownloadBillRequest() {
		super("alipay.data.dataservice.bill.downloadurl.query");
	}
	
	private String billType = "trade";
	private String billDate;

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("bill_type", billType);
		bean.put("bill_date", billDate);
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	
}
