package shoppingmall.alipay;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class JsonUtil {
	
	private boolean formatDate;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public JsonUtil(boolean formatDate){
		this.formatDate = formatDate;
	}

	public String bean2Json(Object obj)throws Exception{
		if(obj == null){
			return null;
		}
		if((obj instanceof Boolean)||(obj instanceof Number)){
			return obj.toString();
		}
		if(obj instanceof Date){
			if(formatDate){
				return df.format((Date)obj);
			}else{
				return String.valueOf(((Date)obj).getTime());
			}
		}
		if(obj instanceof String){
			return (String)obj;
		}
		if(obj instanceof Currency){
			return ((Currency)obj).toString();
		}
		if(obj instanceof List){
			return List2Json((List)obj);
		}
		if(obj instanceof Map){
			return Map2Json((Map)obj);
		}
		return obj2Json(obj);
	}

	private String obj2Json(Object obj) throws Exception {
		
		Class cls = obj.getClass();
		Method[] methods = cls.getDeclaredMethods();
		TreeMap<String, String> map = new TreeMap<String, String>();
		for(Method m : methods){
			String name = m.getName();
			boolean hadget = name.startsWith("get");
			boolean noparam = m.getParameterTypes().length == 0;
			if(!hadget || !noparam){
				continue;
			}
			name = name.substring(3).toLowerCase();
			Object rs = m.invoke(obj, (Object[])null);
			String value = bean2Json(rs);
			if(value != null){
				map.put(name, value);
			}
		}
		if(map.size() <= 0){
			return obj.toString();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(String key : map.keySet()){
			if(sb.length() > 1){
				sb.append(",");
			}
			String value = map.get(key);
			addField(sb, key, value);
		}
		sb.append("}");
		return sb.toString();
	}

	public String Map2Json(Map bean)throws Exception {
		TreeMap<String, String> map = new TreeMap<String, String>();
		for(Object key : bean.keySet()){
			Object v = bean.get(key);
			if(v == null){
				continue;
			}
			//System.out.println(key + ":" + v);
			String value = bean2Json(v);
			map.put(key.toString(), value);
		}
		if(map.size() <= 0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(String key : map.keySet()){
			if(sb.length() > 1){
				sb.append(",");
			}
			String value = map.get(key);
			addField(sb, key, value);
		}
		sb.append("}");
		return sb.toString();
	}

	public String List2Json(List list)throws Exception{
		TreeSet<String> set = new TreeSet<String>();
		for(Object obj : list){
			String value = bean2Json(obj);
			if(value != null){
				set.add(value);
			}
		}
		if(set.size() <= 0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(String str : set){
			if(sb.length() > 1){
				sb.append(",");
			}
			sb.append(str);
		}
		sb.append("]");
		return sb.toString();
	}


	private static void addField(StringBuffer sb, String key, String value) {
		sb.append("\"").append(key).append("\":");
		if(value != null){
			if(value.startsWith("{") && value.endsWith("}")){
				sb.append(value);
				return ;
			}
			if(value.startsWith("[") && value.endsWith("]")){
				sb.append(value);
				return ;
			}
		}
		sb.append("\"").append(value).append("\"");
	}
}
