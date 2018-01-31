package shoppingmall.alipay;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public abstract class APayRequest extends AppConst{
	
	public static enum SignType{
		RSA,
		RSA2
	}
	
	protected final Logger log = Logger.getLogger(this.getClass());

	private String notify_url = getNotifyUrl();
	private String format = "JSON";
	private String method;
	private String charset = "utf-8";
	private String sign_type = "RSA2";
	private String sign;																																																																																																																																													
	private String timestamp;
	private String version = "1.0";
	private String app_auth_token;
	private String biz_content;
	
	private String return_url;
	
	public APayRequest(String method){
		this.method = method;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.timestamp = df.format(new Date());
	}
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getMethod() {
		return method;
	}
	public String getCharset() {
		return charset;
	}
	public String getSign_type() {
		return sign_type;
	}
	public String getSign() {
		return sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVersion() {
		return version;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public String getApp_auth_token() {
		return app_auth_token;
	}
	
	public void setApp_auth_token(String app_auth_token) {
		this.app_auth_token = app_auth_token;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	private static Set<String> methods = new HashSet<String>();
	
	static{
		methods.add("alipay.trade.app.pay");
		methods.add("alipay.trade.pay");
		methods.add("alipay.trade.close");
		methods.add("alipay.trade.precreate");
		methods.add("alipay.trade.create");
	}
	
	public final String toQueryString()throws Exception{
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		
		map.put("app_id", getAppId());
		map.put("method", method);
		map.put("format", format);
		map.put("charset", charset);
		map.put("timestamp", timestamp);
		map.put("version", version);
		if(return_url != null){
			map.put("return_url", return_url);
		}
		if(methods.contains(method)){
			map.put("notify_url", notify_url);
		}
		if(app_auth_token != null){
			map.put("app_auth_token", app_auth_token);
		}
		
		for(String key : extParam.keySet()){
			String value = extParam.get(key);
			map.put(key, value);
		}
		
		TreeMap<String, Object> bean = new TreeMap<String, Object>();
		addFields(bean);
		biz_content = (new JsonUtil(true)).bean2Json(bean);
		
		map.put("biz_content", biz_content);
		map.put("sign_type", sign_type);
		
		buildSign(map, sign_type);
		
		map.put("sign", sign);
		
		return Util.buildURLQueryString(map);
	}

	private void buildSign(TreeMap<String, String> bean, String signType)throws Exception {
		String queryString = Util.buildQueryString(bean);
		//byte[] result = Encrypt.encode(queryString, accessKeySecret, sign_type);
		//sign = new String(result, "UTF-8");
		if("RSA2".equals(signType)){
			sign = Sign.rsa256Sign(queryString, getPrivateKey(), "UTF-8");
		}else if("RSA".equals(signType)){
			sign = Sign.rsaSign(queryString, getPrivateKey(), "UTF-8");
		}else{
			throw new RuntimeException("not.support.sign.type");
		}
		log.debug("signString:" + queryString);
		log.debug("signType:" + signType);
		log.debug("sign:" + sign);
	}

	protected abstract void addFields(TreeMap<String, Object> bean);

	private Map<String, String> extParam =new HashMap<String, String>();
	
	public void addExtParam(String key, String value){
		extParam.put(key, value);
	}
}
