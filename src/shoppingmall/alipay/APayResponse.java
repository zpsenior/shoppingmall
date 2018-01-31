package shoppingmall.alipay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class APayResponse extends AppConst{
	protected final Logger log = Logger.getLogger(this.getClass());
	
	private String nodeName;
	
	private String sign;
	
	private String code;
	private String msg;
	private String sub_code;
	private String sub_msg;
	
	APayResponse(String nodeName){
		this.nodeName = nodeName;
	}
	
	public void parseJson(String json)throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(json);
		JsonNode nodeSign = root.get("sign");
		sign = nodeSign.asText();
		JsonNode nodeBody = root.get(nodeName);
		String txt = nodeBody.toString();
		checkSign(sign, txt);
		code = getText(nodeBody, "code");
		msg = getText(nodeBody, "msg");
		sub_code = getText(nodeBody, "sub_code");
		sub_msg = getText(nodeBody, "sub_msg");
		setFields(nodeBody);
	}

	private void checkSign(String sign, String text)throws Exception{
		if(text.contains("http://")){
			text = text.replaceAll("/", "\\\\/");
		}
		log.debug("response check text:" + text);
		log.debug("response check sign:" + sign);
		if(!Sign.rsaCheck(text, sign, getPublicKey(), "utf-8", signType)){
			throw new RuntimeException("check.sign.error");
		}
	}

	protected <T> List<T> getArray(JsonNode node, String name, Class<T> cls) {
		JsonNode jn = node.get(name);
		if(jn == null){
			return null;
		}
		Iterator<Entry<String, JsonNode>> iter = jn.getFields();
		List<T> list = new ArrayList<T>();
		while(iter.hasNext()){
			Entry<String, JsonNode> entry = iter.next();
			JsonNode n = entry.getValue();
			T obj = parseClass(n, cls);
			list.add(obj);
		}
		return list;
	}
	
	protected <T> T parseClass(JsonNode node, Class<T> cls){
		return null;
	}
	
	protected String getText(JsonNode node, String name){
		JsonNode jn = node.get(name);
		if(jn == null){
			return null;
		}
		return jn.asText();
	}
	
	protected long getLong(JsonNode node, String name){
		JsonNode jn = node.get(name);
		if(jn == null){
			return 0;
		}
		return jn.asLong();
	}
	
	protected int getInt(JsonNode node, String name){
		JsonNode jn = node.get(name);
		if(jn == null){
			return 0;
		}
		return jn.asInt();
	}

	protected Currency getCurrency(JsonNode node, String name){
		JsonNode jn = node.get(name);
		if(jn == null){
			return Currency.ZERO;
		}
		return new Currency(jn.asText());
	}

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	protected Date getDate(JsonNode node, String name)throws Exception{
		JsonNode jn = node.get(name);
		if(jn == null){
			return null;
		}
		return df.parse(jn.asText());
	}
	
	protected abstract void setFields(JsonNode node)throws Exception;
	
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public String getSub_code() {
		return sub_code;
	}
	public String getSub_msg() {
		return sub_msg;
	}
	public String getSign() {
		return sign;
	}
	
	private String signType = "RSA";

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	

}
