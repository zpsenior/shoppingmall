package shoppingmall.alipay;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import shoppingmall.exception.DataValidateException;
import shoppingmall.exception.ThirdSysErrException;

public class APayService extends AppConst {
	
	protected final Logger log = Logger.getLogger(this.getClass());

	protected final HttpClient getHttpClient(){
		HttpClient cli = new HttpClient();
		
		return cli;
	}

	public final static String urlAddr = "https://openapi.alipay.com/gateway.do";

	public APayTradeCreateResponse createTrade(APayTradeCreateRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayTradeCreateResponse response = new APayTradeCreateResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayTradePayResponse payTrade(APayTradePayRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayTradePayResponse response = new APayTradePayResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayTradeQueryResponse queryTrade(APayTradeQueryRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayTradeQueryResponse response = new APayTradeQueryResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayTradeCloseResponse closeTrade(APayTradeCloseRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayTradeCloseResponse response = new APayTradeCloseResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayTradeRefundResponse refundTrade(APayTradeRefundRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayTradeRefundResponse response = new APayTradeRefundResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayTradeRefundQueryResponse queryRefundTrade(APayTradeRefundQueryRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayTradeRefundQueryResponse response = new APayTradeRefundQueryResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayAccountTransferResponse transferAccount(APayAccountTransferRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayAccountTransferResponse response = new APayAccountTransferResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayUserCertifyInitResponse userCertifyInit(APayUserCertifyInitRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayUserCertifyInitResponse response = new APayUserCertifyInitResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayUserCertifyResponse userCertify(APayUserCertifyRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayUserCertifyResponse response = new APayUserCertifyResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayUserCertifyQueryResponse userCertifyQuery(APayUserCertifyQueryRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayUserCertifyQueryResponse response = new APayUserCertifyQueryResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayUserVerifyResponse userVerify(APayUserVerifyRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayUserVerifyResponse response = new APayUserVerifyResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayUserCreditScoreResponse getUserCreditScore(APayUserCreditScoreRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayUserCreditScoreResponse response = new APayUserCreditScoreResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayUserWatchlistResponse getUserWatchlistScore(APayUserWatchlistRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayUserWatchlistResponse response = new APayUserWatchlistResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayDownloadBillResponse downloadBill(APayDownloadBillRequest request)throws Exception{
		String result = execute(request, urlAddr);
		APayDownloadBillResponse response = new APayDownloadBillResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}

	public APayOpenAuthTokenAppResponse openAuthToken(APayOpenAuthTokenAppRequest request) throws Exception{
		String result = execute(request, urlAddr);
		APayOpenAuthTokenAppResponse response = new APayOpenAuthTokenAppResponse();
		response.setSignType(request.getSign_type());
		response.parseJson(result);
		return response;
	}
	
	public APayDayBill downloadDayBill(APayDownloadBillRequest request)throws Exception{
		APayDownloadBillResponse response = downloadBill(request);
		if(!"10000".equals(response.getCode())){
			if("isp.bill_not_exist".equals(response.getSub_code())){
				return new APayDayBill();
			}
			throw new DataValidateException("apay.response.error", response.getMsg() + ":" + response.getSub_code() + ":" + response.getSub_msg());
		}
		String url = response.getBill_download_url();
		log.debug("download url:" + url);
		return downloadUrl(url, request.getBillDate());
	}
	
	private APayDayBill downloadUrl(String url, String billDate)throws Exception{
		HttpClient cli = getHttpClient();
		GetMethod method = new GetMethod(url);
		cli.executeMethod(method);
		
		InputStream is = method.getResponseBodyAsStream();
		
		Map<String, byte[]> map =  StreamUtil.unzip(is);
		
		log.debug("download file: " + map.keySet());
		
		APayDayBill sum = null;
		List<APayDayBillItem> items = null;
		for(String key : map.keySet()){
			ByteArrayInputStream bis = new ByteArrayInputStream(map.get(key));
			List<String> lst = StreamUtil.read2Lines(bis);
			if(key.indexOf('(') > 0){
				sum = APayDayBill.readDayBill(lst);
			}else{
				items = APayDayBillItem.readBillItems(lst);
			}
		}
		sum.setTradedate(billDate);
		sum.setItems(items);
		sum.checkItem();
		return sum;
	}

	private String execute(APayRequest request, String addr)throws Exception{
		HttpClient cli = getHttpClient();
		
		String queryString = request.toQueryString();
		
		String url = addr + "?" + queryString;
		
		log.debug(url);
		
		GetMethod method = new GetMethod(url);
		
		cli.executeMethod(method);
		
		int statusCode = method.getStatusCode();
		if(statusCode != 200){
			log.error("response.status.err:" + statusCode);
			throw new ThirdSysErrException("response.status.err", String.valueOf(statusCode));
		}
		
		String result;
		byte[] bytes = method.getResponseBody();
		result = new String(bytes, "utf-8");
		result = result.trim();
		if(result.endsWith("</html>")|| result.startsWith("<html>")){
			String body = new String(bytes, "gbk");
			log.error(body);
			this.result = body;
			throw new ThirdSysErrException("response.format.err");
		}
		log.debug(result);
		return result;
	}
	
	private String result;

	public String getResult() {
		return result;
	}
	
}
