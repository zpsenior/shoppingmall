package shoppingmall.alipay;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class Util {
	
	private final static DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static int Date2Int(Date date){
		String str = df.format(date);
		return Integer.parseInt(str);
	}
	
	public static Date Int2Date(int date)throws Exception{
		String str = String.valueOf(date);
		return df.parse(str);
	}
	
	public static String buildQueryString(TreeMap<String, String> bean)throws Exception {
		StringBuffer sb = new StringBuffer();
		for(String key : bean.keySet()){
			String value = bean.get(key);
			if(value == null){
				continue;
			}
			if(sb.length() > 0){
				sb.append("&");
			}
			sb.append(key).append("=").append(value);
		}
		return sb.toString();
	}
	
	public static String buildURLQueryString(TreeMap<String, String> bean)throws Exception {
		StringBuffer sb = new StringBuffer();
		for(String key : bean.keySet()){
			String value = bean.get(key);
			if(value == null){
				continue;
			}
			if(sb.length() > 0){
				sb.append("&");
			}
			value = URLEncoder.encode(value, "utf-8");
			sb.append(key).append("=").append(value);
		}
		return sb.toString();
	}
}
