package shoppingmall.alipay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class APayDayBillItem {
	
	private String trade_no;
	private String out_trade_no;
	private String trade_type;
	private String subject;
	private Date gmt_create;
	private Date gmt_payment;

	private String store_id;
	private String store_name;
	private String operator_id;
	private String terminal_id;
	private String buyer_logon_id;
	
	private Currency total_amount = Currency.ZERO;    //订单金额
	private Currency receipt_amount = Currency.ZERO;  //交易金额
	private Currency alipay_red_packet_amount = Currency.ZERO;  //阿里红包
	private Currency point_amount = Currency.ZERO;	  //集分宝抵扣
	private Currency alipay_discount_amount = Currency.ZERO;  //阿里优惠
	private Currency store_discount_amount = Currency.ZERO;  //商家优惠
	private Currency coupon_verify_amount = Currency.ZERO;  //券核销金额
	private String coupon_name;   //优惠券名称
	private Currency store_red_packet_amount = Currency.ZERO;  //商家红包
	private Currency card_consume_amount = Currency.ZERO;  //卡消费金额
	private String out_request_no; //退款批次号
	private Currency fee = Currency.ZERO;  //服务费
	private Currency share_profit = Currency.ZERO;  //分润
	private String remark; //备注

	public APayDayBillItem() {}
	
	private DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Date transDate(String str) throws Exception {
		if(str == null){
			return null;
		}
		return new Date(df.parse(str).getTime());
	}
	
	public APayDayBillItem(String[] values) throws Exception{
		for(int i = 0; i < values.length; i++){
			values[i] = values[i].trim();
		}
		trade_no = values[0];
		out_trade_no = values[1];
		trade_type = values[2];
		subject = values[3];
		gmt_create = transDate(values[4]);
		gmt_payment = transDate(values[5]);
		store_id = values[6];
		store_name = values[7];
		operator_id = values[8];
		terminal_id = values[9];
		buyer_logon_id = values[10];   //对方账户
		total_amount = new Currency(values[11]); //订单金额
		receipt_amount = new Currency(values[12]); //商家实收
		alipay_red_packet_amount = new Currency(values[13]);
		point_amount = new Currency(values[14]);
		alipay_discount_amount = new Currency(values[15]);
		store_discount_amount = new Currency(values[16]);
		coupon_verify_amount = new Currency(values[17]);
		coupon_name = values[18];
		store_red_packet_amount = new Currency(values[19]);
		card_consume_amount = new Currency(values[20]);
		out_request_no = values[21];
		fee = new Currency(values[22]);
		share_profit = new Currency(values[23]);
		if(values.length >= 25){
			remark = values[24];
		}
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}

	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}

	public Currency getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Currency total_amount) {
		this.total_amount = total_amount;
	}

	public Currency getReceipt_amount() {
		return receipt_amount;
	}

	public void setReceipt_amount(Currency receipt_amount) {
		this.receipt_amount = receipt_amount;
	}

	public Currency getPoint_amount() {
		return point_amount;
	}

	public void setPoint_amount(Currency point_amount) {
		this.point_amount = point_amount;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}

	public Currency getFee() {
		return fee;
	}

	public void setFee(Currency fee) {
		this.fee = fee;
	}

	public Currency getShare_profit() {
		return share_profit;
	}

	public void setShare_profit(Currency share_profit) {
		this.share_profit = share_profit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public Currency getAlipay_red_packet_amount() {
		return alipay_red_packet_amount;
	}

	public void setAlipay_red_packet_amount(Currency alipay_red_packet_amount) {
		this.alipay_red_packet_amount = alipay_red_packet_amount;
	}

	public Currency getAlipay_discount_amount() {
		return alipay_discount_amount;
	}

	public void setAlipay_discount_amount(Currency alipay_discount_amount) {
		this.alipay_discount_amount = alipay_discount_amount;
	}

	public Currency getStore_discount_amount() {
		return store_discount_amount;
	}

	public void setStore_discount_amount(Currency store_discount_amount) {
		this.store_discount_amount = store_discount_amount;
	}

	public Currency getCoupon_verify_amount() {
		return coupon_verify_amount;
	}

	public void setCoupon_verify_amount(Currency coupon_verify_amount) {
		this.coupon_verify_amount = coupon_verify_amount;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public Currency getStore_red_packet_amount() {
		return store_red_packet_amount;
	}

	public void setStore_red_packet_amount(Currency store_red_packet_amount) {
		this.store_red_packet_amount = store_red_packet_amount;
	}

	public Currency getCard_consume_amount() {
		return card_consume_amount;
	}

	public void setCard_consume_amount(Currency card_consume_amount) {
		this.card_consume_amount = card_consume_amount;
	}

	public String getPayid(){
		return out_trade_no;
	}
	
	private final static String PREFIX_ROW = "#-----------------------------------------业务明细列表----------------------------------------";
	private final static String SUFFIX_ROW = "#-----------------------------------------业务明细列表结束------------------------------------";
	
	public static List<APayDayBillItem> readBillItems(List<String> lst) throws Exception{
		List<APayDayBillItem> list = new ArrayList<APayDayBillItem>();
		int len = lst.size();
		boolean start = false;
		for(int i = 1; i < len; i++){
			String line = lst.get(i);
			line = line.trim();
			if(PREFIX_ROW.equals(line)){
				start = true;
				i++;
				continue;
			}
			if(SUFFIX_ROW.equals(line)){
				break;
			}
			if(start){
				APayDayBillItem bill = null;
				try{
					bill = new APayDayBillItem(line.split(","));
				}catch(Exception e){
					log.error("line:" + line);
					throw e;
				}
				list.add(bill);
			}
		}
		return list;
	}
	
	protected final static Logger log = Logger.getLogger(APayDayBillItem.class);

}
