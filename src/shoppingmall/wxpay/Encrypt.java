package shoppingmall.wxpay;

import java.security.Provider;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

public class Encrypt {
	public static final String HMAC_SHA1 = "HmacSHA1";  
	public static final String HMAC_SHA256 = "HmacSHA256";
	
	protected static final Logger log = Logger.getLogger(Encrypt.class);
	
	public static void listProvider(){
		Provider[] providers = java.security.Security.getProviders();
		for(Provider p : providers){
			log.debug(p.getName());
		}
	}
    
    /**   
     * 生成签名数据   
     *    
     * @param data 待加密的数据   
     * @param key  加密使用的key   
     * @throws InvalidKeyException   
     * @throws NoSuchAlgorithmException   
     */    
	public static byte[] encode(String data, String key, String arithmetic)throws Exception {
		String algorithm = "";
		if(HMAC_SHA1.equals(arithmetic)){
			algorithm = SignatureMethod.HMAC_SHA1;
		}else if(HMAC_SHA256.equals(arithmetic)){
			//algorithm = SignatureMethod.
		}else{
			throw new RuntimeException("not.support.arithmetic:" + arithmetic);
		}
		//listProvider();
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, algorithm);
		Mac mac = Mac.getInstance(arithmetic);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data.getBytes());
		return base64(rawHmac);
	}
	
	public static byte[] base64(byte[] buff){
		Base64 base64 = new Base64();
		return base64.encode(buff);
	}
	
	public static String bytesToHexString(byte[] buff) {
		StringBuilder sb = new StringBuilder();
		for (byte b : buff) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}
      
	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0f];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
	
	public static String randomString(int len){
		return RandomStringUtils.random(len, chars);
	}
	
	private final static char[] chars = new char[]{
		'a','b','c','d','e','f','g', 
		'h','i','j','k','l','m','n', 
		'o','p','q','r','s','t', 
		'u','v','w','x','y','z',
		'A','B','C','D','E','F','G', 
		'H','I','J','K','L','M','N', 
		'O','P','Q','R','S','T', 
		'U','V','W','X','Y','Z',
		'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
}
