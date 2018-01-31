package shoppingmall.wxpay;

import java.security.MessageDigest;
import java.util.TreeMap;

public class MD5 {
    public final static String encode(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
	public static String formatQueryString(TreeMap<String, String> map, String hashCode)throws Exception {
		StringBuffer sb = new StringBuffer();
		for(String key : map.keySet()){
			String value = map.get(key);
			sb.append(key);
			sb.append(value);
		}
		hashCode = MD5.encode(hashCode).toLowerCase();
		sb.append(hashCode);
		String token = sb.toString();
		token = new String(token.getBytes(), "UTF-8");
		return token;
	}
}
