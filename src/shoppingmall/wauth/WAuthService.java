package shoppingmall.wauth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import shoppingmall.exception.ThirdSysErrException;
import shoppingmall.wauth.WAuthAccessTokenRequest;
import shoppingmall.wauth.WAuthAccessTokenResponse;
import shoppingmall.wauth.WAuthGetSessionRequest;
import shoppingmall.wauth.WAuthGetSessionResponse;
import shoppingmall.wauth.WAuthRefreshTokenRequest;
import shoppingmall.wauth.WAuthRefreshTokenResponse;
import shoppingmall.wauth.WAuthRequest;
import shoppingmall.wauth.WAuthResponse;

public class WAuthService {
	
	private final static String urlAddr = "https://api.weixin.qq.com";
	
	private final static String urlGetAccessToken = "/cgi-bin/token";
	
	private final static String urlGetTicket = "/cgi-bin/ticket/getticket";
	
	private final static String urlAccessToken = "/sns/oauth2/access_token";
	
	private final static String urlRefreshToken = "/sns/oauth2/refresh_token";
	
	private final static String urlGetSessionToken = "/sns/jscode2session";
	
	protected final Logger log = Logger.getLogger(this.getClass());

	protected final HttpClient getHttpClient(){
		HttpClient cli = new HttpClient();
		
		return cli;
	}
	
	private String appid;
	
	private String appSecret;
	
	public WAuthService(String appid, String appSecret){
		this.appid = appid;
		this.appSecret = appSecret;
	}
	
	public String getAccessToken()throws Exception{
		
		String queryString = "grant_type=client_credential&appid=" + appid + "&secret=" + appSecret;
		
		Map<String, String> bean = execute(urlAddr + urlGetAccessToken, queryString);
		
		return bean.get("access_token");
	}
	
	public String getTicket(String access_token)throws Exception{
		
		String queryString = "access_token=" + access_token + "&type=jsapi";
		
		Map<String, String> bean = execute(urlAddr + urlGetTicket, queryString);
		
		return bean.get("ticket");
	}
	
	public WAuthAccessTokenResponse accessToken(WAuthAccessTokenRequest request)throws Exception{
		
		WAuthAccessTokenResponse response = new WAuthAccessTokenResponse();
		
		Map<String, String> bean = execute(request, urlAddr + urlAccessToken, response);
		
		response.parse(bean);
		
		return response;
	}
	
	public WAuthRefreshTokenResponse refreshToken(WAuthRefreshTokenRequest request)throws Exception{
		
		WAuthRefreshTokenResponse response = new WAuthRefreshTokenResponse();
		
		Map<String, String> bean = execute(request, urlAddr + urlRefreshToken, response);
		
		response.parse(bean);
		
		return response;
	}
	
	public WAuthGetSessionResponse getSession(WAuthGetSessionRequest request)throws Exception{
		
		WAuthGetSessionResponse response = new WAuthGetSessionResponse();
		
		Map<String, String> bean = execute(request, urlAddr + urlGetSessionToken, response);
		
		response.parse(bean);
		
		return response;
	}
	
	
	private Map<String, String> execute(WAuthRequest request, String addr, WAuthResponse response)throws Exception{
		
		request.setAppid(appid);
		request.setAppSecret(appSecret);
		
		String queryString = request.toQueryString();
		
		return execute(addr, queryString);

	}

	private Map<String, String> execute(String addr, String queryString) throws Exception {
		HttpClient cli = getHttpClient();
		String url = addr + "?" + queryString;
		
		GetMethod method = new GetMethod(url);
		
		cli.executeMethod(method);
		
		int statusCode = method.getStatusCode();

		if(statusCode != 200){
			String str = statusCode + ":" + method.getStatusText() + "(" + method.getStatusLine() + ")";
			log.error(str);
			throw new ThirdSysErrException("wauth.error", str);
		}
		
		String result = method.getResponseBodyAsString();
		result = result.trim();
		
		log.debug("response status: " + statusCode);
		log.debug("response body: " + result);
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(result);
		String errCode = getText(root, "errcode");
		String errMsg = getText(root, "errmsg");
		if(errCode != null){
			if(!"0".equals(errCode)){
				String str = errCode + ":" + errMsg;
				log.error(str);
				throw new ThirdSysErrException("wauth.error", str);
			}
		}
		
		Iterator<String> iter = root.getFieldNames();
		
		Map<String, String> bean = new HashMap<String, String>();
		
		while(iter.hasNext()){
			String key = iter.next();
			String value = root.get(key).asText();
			bean.put(key, value);
		}
		
		return bean;
	}
	
	protected String getText(JsonNode node, String name){
		JsonNode jn = node.get(name);
		if(jn == null){
			return null;
		}
		return jn.asText();
	}

}
