package shoppingmall.alipay;

import java.util.TreeMap;

public class APayUserVerifyRequest extends APayRequest {

	public APayUserVerifyRequest() {
		super("zhima.credit.antifraud.verify");
	}
	
	private String product_code;
	private String transaction_id;
	private String cert_no;
	private String cert_type;
	private String name;
	private String mobile;
	private String email;
	private String bank_card;
	private String address;
	private String ip;
	private String mac;
	private String wifimac;
	private String imei;

	@Override
	protected void addFields(TreeMap<String, Object> bean) {
		bean.put("product_code", product_code);
		bean.put("transaction_id", transaction_id);
		bean.put("cert_no", cert_no);
		bean.put("cert_type", cert_type);
		bean.put("name", name);
		bean.put("mobile", mobile);
		bean.put("email", email);
		bean.put("bank_card", bank_card);
		bean.put("address", address);
		bean.put("ip", ip);
		bean.put("mac", mac);
		bean.put("wifimac", wifimac);
		bean.put("imei", imei);
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBank_card() {
		return bank_card;
	}

	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getWifimac() {
		return wifimac;
	}

	public void setWifimac(String wifimac) {
		this.wifimac = wifimac;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	
}
