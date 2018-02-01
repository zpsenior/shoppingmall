package shoppingmall.wauth;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

import shoppingmall.pub.POBase;

public class SharedLink extends POBase {
	private String appid;
	private String url;
	private String jsapi_ticket;
	private String noncestr;
	private String timestamp;
	private String signature;
	
	public SharedLink(String appid, String jsapi_ticket, String url)throws Exception{
		this.appid = appid;
		this.jsapi_ticket = jsapi_ticket;
		this.url = url;
		timestamp = Long.toString(System.currentTimeMillis() / 1000);
		noncestr = UUID.randomUUID().toString();
		
		String token = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(token.getBytes("UTF-8"));
        signature = byteToHex(crypt.digest());
	}
	
	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	
	public String getUrl() {
		return url;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public String getAppid() {
		return appid;
	}

}
