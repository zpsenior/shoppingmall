package shoppingmall.wxpay;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TreeMap;

import org.apache.log4j.Logger;


public abstract class WPayRequest extends AppConst{
	protected final Logger log = Logger.getLogger(this.getClass());
	
	//public final static String appid = "wx27ab1e596dee1b09";
	//public final static String mchId = "1486618222";
	
	public final static String deviceInfo = "WEB";

	public final static String SIGN_TYPE_SHA256 = "HMAC-SHA256";
	
	public final static String TAR_TYPE_GZ = "GZIP";
	
	private String nonceStr;
	
	private String sign;
	
	private String signType;
	
	private String tarType;
	
	public WPayRequest(String sign, String tarType){
		this.signType = sign;
		this.tarType = tarType;
		this.nonceStr = Encrypt.randomString(32);
	}

	public boolean isTarType() {
		return TAR_TYPE_GZ.equals(tarType);
	}


	protected String buildSign(TreeMap<String, String> map, String signType)throws Exception {
		StringBuffer sb = new StringBuffer();
		for(String key : map.keySet()){
			String value = map.get(key);
			if(value == null || "".equals(value)){
				continue;
			}
			if(sb.length() > 0){
				sb.append("&");
			}
			sb.append(key).append("=").append(value);
		}
		sb.append("&key=").append(getAppKey());
		String sign = sb.toString();
		log.debug("toSign:" + sign);
		if(SIGN_TYPE_SHA256.equals(signType)){
			byte[] buff = Encrypt.encode(sign, getAppKey(), Encrypt.HMAC_SHA256);
			sign = new String(buff).toUpperCase();
		}else{ //MD5
			sign = MD5.encode(sign).toUpperCase();
		}
		log.debug("sign:" + sign);
		return sign;
	}
	
	protected abstract void addFields(TreeMap<String, String> map);
	
	public String toXml()throws Exception{
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		
		map.put("appid", getAppId());
		map.put("mch_id", getMchId());
		map.put("device_info", deviceInfo);
		map.put("nonce_str", nonceStr);
		
		if(signType != null){
			map.put("sign_type", signType);
		}
		
		if(TAR_TYPE_GZ.equals(tarType)){
			map.put("tar_type", tarType);
		}
		
		addFields(map);
		
		sign = buildSign(map, signType);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		pw.println("<xml>");
		for(String key : map.keySet()){
			String value = map.get(key);
			if(value != null && !"".equals(value)){
				pw.append("<").append(key).append(">");
				pw.append("<![CDATA[").append(value).append("]]>");
				pw.append("</").append(key).append(">");
				pw.println();
			}
		}
		pw.append("<sign>").append(sign).println("</sign>");
		pw.println("</xml>");
		
		String xml = sw.toString();
		
		log.debug(xml);
		
		return xml;
	}

	public String getSign() {
		return sign;
	}

	public String getNoncestr() {
		return nonceStr;
	}
	
}
