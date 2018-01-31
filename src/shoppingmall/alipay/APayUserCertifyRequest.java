package shoppingmall.alipay;

import java.util.TreeMap;

public class APayUserCertifyRequest extends APayRequest {

	public APayUserCertifyRequest() {
		super("zhima.customer.certification.certify");
	}
	
	private String biz_no;

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("biz_no", biz_no);
	}

	public String getBiz_no() {
		return biz_no;
	}

	public void setBiz_no(String biz_no) {
		this.biz_no = biz_no;
	}

	
}
