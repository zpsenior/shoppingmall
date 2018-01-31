package shoppingmall.alipay;

import java.util.TreeMap;

public class APayUserCertifyInitRequest extends APayRequest {

	public APayUserCertifyInitRequest() {
		super("zhima.customer.certification.initialize");
	}
	
	private String transaction_id;
	private String product_code = "w1010100000000002978";
	private String biz_code;
	private String identity_param;
	private String ext_biz_param;

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("transaction_id", transaction_id);
		bean.put("product_code", product_code);
		bean.put("biz_code", biz_code);
		bean.put("identity_param", identity_param);
		bean.put("ext_biz_param", ext_biz_param);
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getBiz_code() {
		return biz_code;
	}

	public void setBiz_code(String biz_code) {
		this.biz_code = biz_code;
	}

	public String getIdentity_param() {
		return identity_param;
	}

	public void setIdentity_param(String identity_param) {
		this.identity_param = identity_param;
	}

	public String getExt_biz_param() {
		return ext_biz_param;
	}

	public void setExt_biz_param(String ext_biz_param) {
		this.ext_biz_param = ext_biz_param;
	}

	
}
