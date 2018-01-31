package shoppingmall.wxpay;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import shoppingmall.exception.DataValidateException;

public class WPayDayBillItem extends WPayResponse {
	
	private Date trade_time; //交易时间
	private String sub_mch_id; //子商户号
	private String transaction_id; //微信订单号
	private String out_trade_no; //商户订单号
	private String openid; //用户标识
	private String trade_type; //交易类型
	private String trade_state; //交易状态
	private String bank_type; //付款银行
	private String fee_type; //货币种类
	private int total_fee; //总金额
	private int coupon_fee; //代金券或立减优惠金额
	
	private String refund_id; //微信退款单号
	private String out_refund_no; //商户退款单号
	private int refund_fee; //退款金额
	private int coupon_refund_fee; //代金券或立减优惠退款金额
	private String refund_channel; //退款类型
	private String refund_status; //退款状态
	
	private String refund_apply_time;   //退款申请时间
	private String refund_success_time; //退款成功时间
	
	private String body; //商品名称
	private String attach; //商户数据包
	private BigDecimal charge; //手续费
	private BigDecimal charge_rate; //费率
	
	private String billType;

	public WPayDayBillItem(){}

	public WPayDayBillItem(String[] values, String billType) throws Exception{
		if(WPayDownloadRequest.BILL_TYPE_ALL.equals(billType)){
			allBill(values);
			this.billType = "4";
		}else if(WPayDownloadRequest.BILL_TYPE_SUCCESS.equals(billType)){
			successBill(values);
			this.billType = "1";
		}else if(WPayDownloadRequest.BILL_TYPE_REFUND.equals(billType)){
			refundBill(values);
			this.billType = "2";
		}else{
			throw new DataValidateException("error.bill.type");
		}
		
	}
	
	private void allBill(String[] values) throws Exception{
		trade_time = transDate(values[0]);//交易时间
		setAppid(values[1]);//公众账号ID
		setMch_id(values[2]);//商户号
		sub_mch_id = values[3];//子商户号
		setDevice_info(values[4]);//设备号
		transaction_id = values[5];//微信订单号
		out_trade_no = values[6];//商户订单号
		openid = values[7];//用户标识
		trade_type = values[8];//交易类型
		trade_state = values[9];//交易状态
		bank_type = values[10];//付款银行
		fee_type = values[11];//货币种类
		total_fee = dbl2Int(values[12]);//总金额
		coupon_fee = dbl2Int(values[13]);//代金券或立减优惠金额
		refund_id = values[14];//微信退款单号
		out_refund_no = values[15];//商户退款单号
		refund_fee = dbl2Int(values[16]);//退款金额
		coupon_refund_fee = dbl2Int(values[17]);//代金券或立减优惠退款金额
		refund_channel = values[18];//退款类型
		refund_status = values[19];//退款状态
		body = values[20];//商品名称
		attach = values[21];//商户数据包
		charge = dbl2Dec(values[22]);//手续费
		charge_rate = parseRate(values[23]);//费率
	}
	
	private void successBill(String[] values)throws Exception{
		trade_time = transDate(values[0]);//交易时间
		setAppid(values[1]);//公众账号ID
		setMch_id(values[2]);//商户号
		sub_mch_id = values[3];//子商户号
		setDevice_info(values[4]);//设备号
		transaction_id = values[5];//微信订单号
		out_trade_no = values[6];//商户订单号
		openid = values[7];//用户标识
		trade_type = values[8];//交易类型
		trade_state = values[9];//交易状态
		bank_type = values[10];//付款银行
		fee_type = values[11];//货币种类
		total_fee = dbl2Int(values[12]);//总金额
		coupon_fee = dbl2Int(values[13]);//代金券或立减优惠金额
		body = values[14];//商品名称
		attach = values[15];//商户数据包
		charge = dbl2Dec(values[16]);//手续费
		charge_rate = parseRate(values[17]);//费率
	}
	
	private void refundBill(String[] values)throws Exception{
		trade_time = transDate(values[0]);//交易时间
		setAppid(values[1]);//公众账号ID
		setMch_id(values[2]);//商户号
		sub_mch_id = values[3];//子商户号
		setDevice_info(values[4]);//设备号
		transaction_id = values[5];//微信订单号
		out_trade_no = values[6];//商户订单号
		openid = values[7];//用户标识
		trade_type = values[8];//交易类型
		trade_state = values[9];//交易状态
		bank_type = values[10];//付款银行
		fee_type = values[11];//货币种类
		total_fee = dbl2Int(values[12]);//总金额
		coupon_fee = dbl2Int(values[13]);//代金券或立减优惠金额
		refund_apply_time = values[14];//退款申请时间
		refund_success_time = values[15];//退款成功时间
		refund_id = values[16];//微信退款单号
		out_refund_no = values[17];//商户退款单号
		refund_fee = dbl2Int(values[18]);//退款金额
		coupon_refund_fee = dbl2Int(values[19]);//代金券或立减优惠退款金额
		refund_channel = values[20];//退款类型
		refund_status = values[21];//退款状态
		body = values[22];//商品名称
		attach = values[23];//商户数据包
		charge = dbl2Dec(values[24]);//手续费
		charge_rate = parseRate(values[25]);//费率
	}
	
	private DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Date transDate(String str) throws Exception {
		if(str == null){
			return null;
		}
		return new Date(df.parse(str).getTime());
	}
	
	private int dbl2Int(String value){
		BigDecimal num = new BigDecimal(value);
		num = num.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
		return num.intValue();
	}
	
	private BigDecimal dbl2Dec(String value){
		BigDecimal dec = new BigDecimal(value);
		dec = dec.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
		return dec;
	}
	
	private BigDecimal parseRate(String value){
		value = value.substring(0, value.length() - 1);
		BigDecimal num = new BigDecimal(value);
		num = num.divide(BigDecimal.TEN).divide(BigDecimal.TEN);
		return num;
	}

	private DateFormat dformat =new SimpleDateFormat("yyyyMMdd");

	public String getTradedate(){
		return dformat.format(trade_time);
	}

	public Date getTrade_time() {
		return trade_time;
	}


	public String getSub_mch_id() {
		return sub_mch_id;
	}


	public String getTransaction_id() {
		return transaction_id;
	}


	public String getOut_trade_no() {
		return out_trade_no;
	}


	public String getOpenid() {
		return openid;
	}


	public String getTrade_type() {
		return trade_type;
	}


	public String getTrade_state() {
		return trade_state;
	}


	public String getBank_type() {
		return bank_type;
	}


	public String getFee_type() {
		return fee_type;
	}


	public int getTotal_fee() {
		return total_fee;
	}


	public int getCoupon_fee() {
		return coupon_fee;
	}


	public String getRefund_id() {
		return refund_id;
	}


	public String getOut_refund_no() {
		return out_refund_no;
	}


	public int getRefund_fee() {
		return refund_fee;
	}


	public int getCoupon_refund_fee() {
		return coupon_refund_fee;
	}


	public String getRefund_channel() {
		return refund_channel;
	}


	public String getRefund_status() {
		return refund_status;
	}


	public String getBody() {
		return body;
	}


	public String getAttach() {
		if(attach.length() > 128){
			return attach.substring(0, 128);
		}
		return attach;
	}


	public BigDecimal getCharge() {
		return charge;
	}


	public BigDecimal getCharge_rate() {
		return charge_rate;
	}

	public String getPayid(){
		return out_trade_no;
	}

	public String getRefund_apply_time() {
		return refund_apply_time;
	}

	public String getRefund_success_time() {
		return refund_success_time;
	}
	
	public String getBillType() {
		return billType;
	}

	@Override
	public void startElement(String key, String value) throws Exception {
	}	
}
