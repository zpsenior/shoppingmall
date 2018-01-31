package shoppingmall.alipay;

import java.util.TreeMap;

public class APayUserWatchlistRequest extends APayRequest {

	public APayUserWatchlistRequest() {
		super("zhima.credit.watchlist.brief.get");
	}
	
	private String transaction_id;
	private String product_code;
	private String cert_type;
	private String cert_no;
	private String name;

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("transaction_id", transaction_id);
		bean.put("product_code", product_code);
		bean.put("cert_type", cert_type);
		bean.put("cert_no", cert_no);
		bean.put("name", name);
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

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
