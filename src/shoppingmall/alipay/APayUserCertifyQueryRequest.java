package shoppingmall.alipay;

import java.util.TreeMap;

public class APayUserCertifyQueryRequest extends APayRequest {

	public APayUserCertifyQueryRequest() {
		super("zhima.customer.certification.query");
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
