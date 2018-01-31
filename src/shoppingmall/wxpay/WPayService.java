package shoppingmall.wxpay;

import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.log4j.Logger;

import shoppingmall.exception.DataValidateException;
import shoppingmall.exception.ThirdSysErrException;

public class WPayService extends AppConst{
	
	protected final Logger log = Logger.getLogger(this.getClass());

	protected final HttpClient getHttpClient(){
		HttpClient cli = new HttpClient();
		
		return cli;
	}

	private final static String urlAddr = "https://api.mch.weixin.qq.com";
	
	private final static String urlDownloadBill = "/pay/downloadbill";
	
	private final static String urlBuildOrder = "/pay/unifiedorder";
	
	private final static String urlCloseOrder = "/pay/closeorder";
	
	private final static String urlQueryOrder = "/pay/orderquery";
	
	private final static String urlRefund = "/secapi/pay/refund";
	
	private final static String urlQueryRefund = "/pay/refundquery";
	
	public WPayBuildOrderResponse buildOrder(WPayBuildOrderRequest request)throws Exception{
		
		String result = execute(request, urlAddr + urlBuildOrder);
		
		WPayBuildOrderResponse response = new WPayBuildOrderResponse();
		
		response.parseXml(result);
		
		checkResponse(response);
		
		return response;
	}
	
	public WPayCloseOrderResponse closeOrder(String signType, String outTradeNo)throws Exception{
		
		WPayCloseOrderRequest request = new WPayCloseOrderRequest(signType);
		
		request.setOutTradeNo(outTradeNo);
		
		String result= execute(request, urlAddr + urlCloseOrder);
		
		WPayCloseOrderResponse response = new WPayCloseOrderResponse();
		
		response.parseXml(result);
		
		checkResponse(response);
		
		return response;
	}
	
	public WPayQueryOrderResponse queryOrder(String signType, String outTradeNo)throws Exception{
		
		WPayQueryOrderRequest request = new WPayQueryOrderRequest(signType);
		
		request.setOutTradeNo(outTradeNo);
		
		String result= execute(request, urlAddr + urlQueryOrder);

		WPayQueryOrderResponse response = new WPayQueryOrderResponse();
		
		response.parseXml(result);
		
		checkResponse(response);
		
		return response;
	}
	
	public WPayRefundResponse refundOrder(WPayRefundRequest request)throws Exception{
		
		String result= execute(request, urlAddr + urlRefund);
		
		WPayRefundResponse response = new WPayRefundResponse();
		
		response.parseXml(result);
		
		checkResponse(response);
		
		return response;
	}
	
	public WPayQueryRefundResponse queryRefundOrder(WPayQueryRefundRequest request)throws Exception{
		
		String result= execute(request, urlAddr + urlQueryRefund);
		
		WPayQueryRefundResponse response = new WPayQueryRefundResponse();
		
		response.parseXml(result);
		
		checkResponse(response);
		
		return response;
	}

	private void checkResponse(WPayResponse response)throws DataValidateException {
		if(!"SUCCESS".equals(response.getReturn_code())){
			String msg = response.getReturn_msg();
			log.error(msg);
			throw new DataValidateException("wpay.build.order.error", msg);
		}
		
		if(!"SUCCESS".equals(response.getResult_code())){
			String msg = response.getErr_code() + ":" + response.getErr_code_des();
			log.error(msg);
			throw new DataValidateException("wpay.build.order.error", msg);
		}
		
		if(!response.vertifySign()){
			throw new DataValidateException("diff.sign.token");
		}
	}
	

	public WPayDayBill downloadBill(WPayDownloadRequest request)throws Exception{
		
		WPayDayBill sum = new WPayDayBill();
		
		String result= execute(request, urlAddr + urlDownloadBill);
		
		if(result.startsWith("<xml>")){
			WPayNotifyResponse notify = new WPayNotifyResponse();
			notify.parseXml(result);
			String code = notify.getReturn_code();
			String msg = notify.getReturn_msg();
			if("No Bill Exist".equals(msg)){
				sum.setTradedate(request.getBillDate());
				return sum;
			}
			throw new ThirdSysErrException("response.status.err", code + ":" + msg);
		}
		
		LineNumberReader reader = new LineNumberReader(new StringReader(result));
		List<String> lst = new ArrayList<String>();
		while(true){
			String line = reader.readLine();
			if(line == null){
				break;
			}
			line = line.trim();
			lst.add(line);
		}

		List<WPayDayBillItem> list = new ArrayList<WPayDayBillItem>();
		int len = lst.size();
		for(int i = 1; i < len; i++){
			String line = lst.get(i);
			if(i == len - 1){
				sum = new WPayDayBill(filterChar(line.split(",")));
				continue;
			}
			if(i == len - 2){
				continue;
			}
			WPayDayBillItem bill = new WPayDayBillItem(filterChar(line.split(",")), request.getBillType());
			list.add(bill);
		}
		sum.setTradedate(request.getBillDate());
		sum.setItems(list);
		sum.checkItem();
		return sum;
	}
	
	private String[] filterChar(String[] values){
		for(int i = 0; i < values.length; i++){
			String value = values[i];
			if(value.startsWith("`")){
				value = value.substring(1);
			}
			values[i] = value.trim();
			System.out.println(i + " : " + value);
		}
		return values;
	}
	
	private static boolean loaded = false;
	
	private void loadCert()throws Exception{
		if(loaded){
			return;
		}
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		InputStream in = WPayService.class.getClassLoader().getResourceAsStream("/cert.p12");
		String password = getMchId();
		try {
            keyStore.load(in, password.toCharArray());
        } finally {
        	in.close();
        }
		ProtocolSocketFactory factory = new AuthSSLProtocolSocketFactory(keyStore, password, keyStore, password);
		Protocol.registerProtocol("https", new Protocol("https", factory, 443));
		loaded = true;
	}
	
	private String execute(WPayRequest request, String url)throws Exception{
		
		loadCert();
		
		HttpClient cli = getHttpClient();
	
		String body = request.toXml();
		
		RequestEntity entity = new StringRequestEntity(body);
		
		PostMethod method = new PostMethod(url);
		
		method.setRequestEntity(entity);
		
		cli.executeMethod(method);
		
		int statusCode = method.getStatusCode();
		if(statusCode != 200){
			log.error("response.status.err:" + statusCode);
			throw new ThirdSysErrException("response.status.err", String.valueOf(statusCode));
		}
		
		String result;
		byte[] bytes = method.getResponseBody();
		if(request.isTarType()){
			bytes = GZIP.uncompress(bytes);
		}
		result = new String(bytes, "utf-8");
		result = result.trim();
		log.debug(result);
		return result;
	}
	
}
