package shoppingmall.alipay;


public class GoodsDetail{
	private String goods_id;
	private String alipay_goods_id;
	private String goods_name;
	private int quantity;
	private Currency price = Currency.ZERO;
	private String goods_category;
	private String body;
	private String show_url;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getAlipay_goods_id() {
		return alipay_goods_id;
	}
	public void setAlipay_goods_id(String alipay_goods_id) {
		this.alipay_goods_id = alipay_goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Currency getPrice() {
		return price;
	}
	public void setPrice(Currency price) {
		this.price = price;
	}
	public String getGoods_category() {
		return goods_category;
	}
	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	
}
