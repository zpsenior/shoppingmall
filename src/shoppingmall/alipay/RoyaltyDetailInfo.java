package shoppingmall.alipay;


public class RoyaltyDetailInfo{
	private int serial_no;
	private String trans_in_type;
	private String batch_no;
	private String out_relation_id;
	private String trans_out_type;
	private String trans_out;
	private String trans_in;
	private Currency amount = Currency.ZERO;
	private String desc;
	private String amount_percentage;
	public int getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}
	public String getTrans_in_type() {
		return trans_in_type;
	}
	public void setTrans_in_type(String trans_in_type) {
		this.trans_in_type = trans_in_type;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getOut_relation_id() {
		return out_relation_id;
	}
	public void setOut_relation_id(String out_relation_id) {
		this.out_relation_id = out_relation_id;
	}
	public String getTrans_out_type() {
		return trans_out_type;
	}
	public void setTrans_out_type(String trans_out_type) {
		this.trans_out_type = trans_out_type;
	}
	public String getTrans_out() {
		return trans_out;
	}
	public void setTrans_out(String trans_out) {
		this.trans_out = trans_out;
	}
	public String getTrans_in() {
		return trans_in;
	}
	public void setTrans_in(String trans_in) {
		this.trans_in = trans_in;
	}
	public Currency getAmount() {
		return amount;
	}
	public void setAmount(Currency amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAmount_percentage() {
		return amount_percentage;
	}
	public void setAmount_percentage(String amount_percentage) {
		this.amount_percentage = amount_percentage;
	}
	
}
