package shoppingmall.pub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Result{
	private String errorCode = "0";
	private String errorMessage;
	private Object data = null;
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public Result(String errorCode, String errorMessage, Object items) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = items;
	}
	
	public Result(Object data) {
		errorCode = "0";
		this.data = data;
	}
	
	@SuppressWarnings({"rawtypes" })
	public static List<Object> reverse(List list){
		List<Object> rs = new ArrayList<Object>();
		int len = list.size();
		for(int i = len - 1; i >= 0; i--){
			Object o = list.get(i);
			rs.add(o);
		}
		return rs;
	}
	
	@SuppressWarnings({"rawtypes" })
	public static List<Object> reverse(Collection cl){
		int len = cl.size();
		List<Object> rs = new ArrayList<Object>();
		for(int i = 0; i < len; i++){
			rs.add(null);
		}
		Iterator iter = cl.iterator();
		while(iter.hasNext()){
			Object obj = iter.next();
			len--;
			rs.set(len, obj);
		}
		return rs;
	}
/*
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("errorCode").append(" : ").append(errorCode);
		sb.append(", ");
		sb.append("errorMessage").append(" : ").append(errorMessage);
		sb.append(", ");
		
		sb.append("}");
		
		return sb.toString();
	}*/
}
