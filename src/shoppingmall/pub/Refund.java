package shoppingmall.pub;

import java.util.Date;

import shoppingmall.alipay.APayResponse;
import shoppingmall.alipay.APayService;
import shoppingmall.alipay.APayTradeRefundQueryRequest;
import shoppingmall.alipay.APayTradeRefundQueryResponse;
import shoppingmall.alipay.APayTradeRefundRequest;
import shoppingmall.alipay.APayTradeRefundResponse;
import shoppingmall.alipay.Currency;
import shoppingmall.exception.DataValidateException;
import shoppingmall.wxpay.WPayQueryRefundRequest;
import shoppingmall.wxpay.WPayQueryRefundResponse;
import shoppingmall.wxpay.WPayRefundRequest;
import shoppingmall.wxpay.WPayRefundResponse;
import shoppingmall.wxpay.WPayResponse;
import shoppingmall.wxpay.WPayService;

public class Refund {

	private String payid;
	private String channel;
	private String outrefundid;
	private int totalfee;
	private int refundfee;
	private String reason;
	private Date refundtime;
	
	private String refundid;
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	

	public void setTotalfee(int totalfee) {
		this.totalfee = totalfee;
	}


	public Refund(String channel, String payid, int refundfee, int itemseq, int time){
		this.payid = payid;
		this.outrefundid = payid + "_REFUND_" + itemseq + "_" + time;
		this.channel = channel;
		this.refundfee = refundfee;
	}


	public Refund(String channel, String payid, int refundfee){
		this.payid = payid;
		this.outrefundid = payid + "_REFUND_ALL";
		this.channel = channel;
		this.refundfee = refundfee;
	}
	
	
	public void refund()throws Exception{
		if("W".equals(channel)){
			wrefund();
		}else if("A".equals(channel)){
			arefund();
		}
	}
	

	
	private void arefund()throws Exception{
		APayTradeRefundRequest request = new APayTradeRefundRequest();
		request.setOut_trade_no(payid);
		request.setRefund_amount(Currency.parseFen(refundfee));
		request.setOut_request_no(outrefundid);
		if(reason != null && !"".equals(reason)){
			request.setRefund_reason(reason);
		}
		APayService serv = new APayService();
		APayTradeRefundResponse response = serv.refundTrade(request);
		refundtime = response.getGmt_refund_pay();
		checkAResponse(response);
	}


	private void checkAResponse(APayResponse response)throws Exception {
		if(!"10000".equals(response.getCode())){
			throw new DataValidateException("apay.refund.error", response.getMsg() + ":" + response.getSub_msg());
		}
	}
	
	private void wrefund()throws Exception{
		WPayRefundRequest request = new WPayRefundRequest(null);
		request.setOut_trade_no(payid);
		request.setOut_refund_no(outrefundid);
		request.setTotal_fee(totalfee);
		request.setRefund_fee(refundfee);
		if(reason != null && !"".equals(reason)){
			request.setRefund_desc(reason);
		}
		WPayService serv = new WPayService();
		WPayRefundResponse response = serv.refundOrder(request);
		checkWResponse(response);
		refundid = response.getRefund_id();
		refundtime = new Date();
	}


	private void checkWResponse(WPayResponse response)throws Exception {
		if(!"SUCCESS".equals(response.getReturn_code())){
			throw new DataValidateException("wpay.refund.error", response.getReturn_msg());
		}
		if(!"SUCCESS".equals(response.getResult_code())){
			throw new DataValidateException("wpay.refund.error", response.getErr_code() + ":" + response.getErr_code_des());
		}
	}

	public String getRefundid() {
		return refundid;
	}
	
	public String getOutrefundid(){
		return outrefundid;
	}
	
	public Date getRefundtime() {
		return refundtime;
	}


	public void queryRefund()throws Exception{
		if("W".equals(channel)){
			queryWrefund();
		}else if("A".equals(channel)){
			queryArefund();
		}
	}
	
	private void queryWrefund()throws Exception{
		WPayQueryRefundRequest request = new WPayQueryRefundRequest(null);
		request.setRefund_id(refundid);
		WPayService serv = new WPayService();
		WPayQueryRefundResponse response = serv.queryRefundOrder(request);
		checkWResponse(response);
	}
	
	private void queryArefund()throws Exception{
		APayTradeRefundQueryRequest request = new APayTradeRefundQueryRequest();
		request.setOut_trade_no(payid);
		request.setOut_request_no(outrefundid);
		APayService serv = new APayService();
		APayTradeRefundQueryResponse response = serv.queryRefundTrade(request);
		checkAResponse(response);
	}
}
