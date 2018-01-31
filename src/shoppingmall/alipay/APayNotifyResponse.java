package shoppingmall.alipay;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class APayNotifyResponse extends AppConst {
	
		private final Logger log = Logger.getLogger(this.getClass());
	
		private Date notify_time;
		private String notify_type;
		private String notify_id;
		private String app_id;
		private String charset;
		private String version;
		private String sign_type;
		private String sign;
		private String trade_no;
		private String out_trade_no;
		private String out_biz_no;
		private String buyer_id;
		private String buyer_logon_id;
		private String seller_id;
		private String seller_email;
		private String trade_status;
		private Currency total_amount = Currency.ZERO;
		private Currency receipt_amount = Currency.ZERO;
		private Currency invoice_amount = Currency.ZERO;
		private Currency buyer_pay_amount = Currency.ZERO;
		private Currency point_amount = Currency.ZERO;
		private Currency refund_fee = Currency.ZERO;
		private String subject;
		private String body;
		private Date gmt_create;
		private Date gmt_payment;
		private Date gmt_refund;
		private Date gmt_close;
		private String fund_bill_list;
		private String passback_params;
		private String voucher_detail_list;
		
		public APayNotifyResponse(){}
		
		@SuppressWarnings("rawtypes")
		public APayNotifyResponse(Map<String, String> map)throws Exception{
			Class cls = this.getClass();
			Field[] fields = cls.getDeclaredFields();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(Field field : fields){
				String key = field.getName();
				String value = map.get(key);
				log.debug(key + ":" + value);
				if(value == null){
					continue;
				}
				String type = field.getType().getName();
				if(type.equals("java.lang.String")){
					field.set(this, value);
				}else if(type.equals("java.util.Date")){
					field.set(this, df.parse(value));
				}else if(type.equals("com.chain.pub.Currency")){
					Currency cur = new Currency(value);
					field.set(this, cur);
				}
			}
			TreeMap<String, String> signMap = new TreeMap<String, String>(map);
			signMap.remove("sign");
			signMap.remove("sign_type");
			queryString = Util.buildQueryString(signMap);
		}
		
		private String queryString;

		public boolean vertifySign() throws Exception{
			log.debug("queryString:" + queryString);
			log.debug("sign:" + sign);
			return Sign.rsaCheck(queryString, sign, getPublicKey(), charset, sign_type);
		}
		
		public Date getNotify_time() {
			return notify_time;
		}
		public void setNotify_time(Date notify_time) {
			this.notify_time = notify_time;
		}
		public String getNotify_type() {
			return notify_type;
		}
		public void setNotify_type(String notify_type) {
			this.notify_type = notify_type;
		}
		public String getNotify_id() {
			return notify_id;
		}
		public void setNotify_id(String notify_id) {
			this.notify_id = notify_id;
		}
		public String getApp_id() {
			return app_id;
		}
		public void setApp_id(String app_id) {
			this.app_id = app_id;
		}
		public String getCharset() {
			return charset;
		}
		public void setCharset(String charset) {
			this.charset = charset;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getSign_type() {
			return sign_type;
		}
		public void setSign_type(String sign_type) {
			this.sign_type = sign_type;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
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
		public String getOut_biz_no() {
			return out_biz_no;
		}
		public void setOut_biz_no(String out_biz_no) {
			this.out_biz_no = out_biz_no;
		}
		public String getBuyer_id() {
			return buyer_id;
		}
		public void setBuyer_id(String buyer_id) {
			this.buyer_id = buyer_id;
		}
		public String getBuyer_logon_id() {
			return buyer_logon_id;
		}
		public void setBuyer_logon_id(String buyer_logon_id) {
			this.buyer_logon_id = buyer_logon_id;
		}
		public String getSeller_id() {
			return seller_id;
		}
		public void setSeller_id(String seller_id) {
			this.seller_id = seller_id;
		}
		public String getSeller_email() {
			return seller_email;
		}
		public void setSeller_email(String seller_email) {
			this.seller_email = seller_email;
		}
		public String getTrade_status() {
			return trade_status;
		}
		public void setTrade_status(String trade_status) {
			this.trade_status = trade_status;
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
		public Currency getInvoice_amount() {
			return invoice_amount;
		}
		public void setInvoice_amount(Currency invoice_amount) {
			this.invoice_amount = invoice_amount;
		}
		public Currency getBuyer_pay_amount() {
			return buyer_pay_amount;
		}
		public void setBuyer_pay_amount(Currency buyer_pay_amount) {
			this.buyer_pay_amount = buyer_pay_amount;
		}
		public Currency getPoint_amount() {
			return point_amount;
		}
		public void setPoint_amount(Currency point_amount) {
			this.point_amount = point_amount;
		}
		public Currency getRefund_fee() {
			return refund_fee;
		}
		public void setRefund_fee(Currency refund_fee) {
			this.refund_fee = refund_fee;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
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
		public Date getGmt_refund() {
			return gmt_refund;
		}
		public void setGmt_refund(Date gmt_refund) {
			this.gmt_refund = gmt_refund;
		}
		public Date getGmt_close() {
			return gmt_close;
		}
		public void setGmt_close(Date gmt_close) {
			this.gmt_close = gmt_close;
		}
		public String getFund_bill_list() {
			return fund_bill_list;
		}
		public void setFund_bill_list(String fund_bill_list) {
			this.fund_bill_list = fund_bill_list;
		}
		public String getPassback_params() {
			return passback_params;
		}
		public void setPassback_params(String passback_params) {
			this.passback_params = passback_params;
		}
		public String getVoucher_detail_list() {
			return voucher_detail_list;
		}
		public void setVoucher_detail_list(String voucher_detail_list) {
			this.voucher_detail_list = voucher_detail_list;
		}
		public String getPayid(){
			return out_trade_no;
		}
}
