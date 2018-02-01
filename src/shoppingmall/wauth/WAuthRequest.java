package shoppingmall.wauth;

import java.net.URLEncoder;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public abstract class WAuthRequest {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	private String appid;
	
	private String appSecret;
	
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	private static String percentEncode(String value) throws Exception {
		if (value == null) return null;
		return URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
	}

	private void buildQueryString(TreeMap<String, String> bean, StringBuffer sb)throws Exception {
		for(String key : bean.keySet()){
			String value = bean.get(key);
			if(value == null){
				continue;
			}
			if(sb.length() > 0){
				sb.append("&");
			}
			value = percentEncode(value);
			sb.append(key).append("=").append(value);
		}
	}
	
	public String toQueryString()throws Exception{
		TreeMap<String, String> bean = new TreeMap<String, String>();
		
		addFields(bean);
		
		bean.put("appid", appid);
		
		bean.put("secret", appSecret);
		
		//String sign = buildSign(bean);
		//bean.put("Signature", sign);

		StringBuffer sb = new StringBuffer();

		buildQueryString(bean, sb);
		String str = sb.toString();
		
		log.debug(str);
		
		return str;
	}

	protected abstract void addFields(TreeMap<String, String> bean);

}
