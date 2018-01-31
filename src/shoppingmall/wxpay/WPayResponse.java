package shoppingmall.wxpay;

import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public abstract class WPayResponse extends AppConst {

	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	
	private String sign_type;
	
	private String return_code;
	private String return_msg;
	
	private String result_code;
	
	private String err_code;
	private String err_code_des;
	
	private String innerSign;
	
	public WPayResponse(){}
	
	public boolean vertifySign() {
		boolean result = innerSign.equals(sign);
		if(!result){
			//log.debug(innerSign + ":" + sign);
		}
		return result;
	}

	protected void buildSign(TreeMap<String, String> bean)throws Exception {
		StringBuffer sb = new StringBuffer();
		for(String key : bean.keySet()){
			String value = bean.get(key);
			if("sign".equals(key)){
				continue;
			}
			if(value == null || "".equals(value)){
				continue;
			}
			if(sb.length() > 0){
				sb.append("&");
			}
			sb.append(key).append("=").append(value);
		}
		sb.append("&key=").append(getAppKey());
		innerSign = sb.toString();
		//log.debug("innerString: " + innerSign);
		if(WPayRequest.SIGN_TYPE_SHA256.equals(sign_type)){
			byte[] buff = Encrypt.encode(sign, getAppKey(), Encrypt.HMAC_SHA256);
			innerSign = new String(buff).toUpperCase();
		}else{
			innerSign = MD5.encode(innerSign).toUpperCase();
		}
	}

	public final String getSign_type() {
		return sign_type;
	}

	public final void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	public final void setAppid(String appid) {
		this.appid = appid;
	}
	public final void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public final void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public final void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public final void setSign(String sign) {
		this.sign = sign;
	}	
	
	public final String getAppid() {
		return appid;
	}
	public final String getMch_id() {
		return mch_id;
	}
	public final String getDevice_info() {
		return device_info;
	}
	public final String getNonce_str() {
		return nonce_str;
	}
	public final String getSign() {
		return sign;
	}

	public final String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public final String getReturn_msg() {
		return return_msg;
	}

	public final void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	public final String getResult_code() {
		return result_code;
	}
	public final void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public final void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public final void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public final String getErr_code() {
		return err_code;
	}
	public final String getErr_code_des() {
		return err_code_des;
	}
	
	public final void parseXml(InputStream is)throws Exception{
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document dt = db.parse(is);
		parseXml(dt);
	}
	
	public final void parseXml(String xml)throws Exception{
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document dt = db.parse(new InputSource(new StringReader(xml)));
		parseXml(dt);
	}
	
	private void parseXml(Document dt)throws Exception{
		Element root = dt.getDocumentElement();
		NodeList children = root.getChildNodes();
		int len = children.getLength();
		TreeMap<String, String> bean = new TreeMap<String, String>();
		for(int i = 0; i < len; i++){
			Node child = children.item(i);
			short type = child.getNodeType();
			if(type == Node.TEXT_NODE){
				continue;
			}
			String key = child.getNodeName();
			String value = child.getTextContent();
			bean.put(key, value);
			if(include(key, value)){
				continue;
			}
			startElement(key, value);
		}
		buildSign(bean);
	}
	
	public boolean include(String key, String value){
		if("appid".equals(key)){
			appid = value;
			return true;
		}else if("mch_id".equals(key)){
			mch_id = value;
			return true;
		}else if("device_info".equals(key)){
			device_info = value;
			return true;
		}else if("nonce_str".equals(key)){
			nonce_str = value;
			return true;
		}else if("sign".equals(key)){
			sign = value;
			return true;
		}else if("result_code".equals(key)){
			result_code = value;
			return true;
		}else if("return_code".equals(key)){
			return_code = value;
			return true;
		}else if("return_msg".equals(key)){
			return_msg = value;
			return true;
		}else if("err_code".equals(key)){
			err_code = value;
			return true;
		}else if("err_code_des".equals(key)){
			err_code_des = value;
			return true;
		}
		return false;	
	}
	
	public abstract void startElement(String key, String value)throws Exception;
	
	private DateFormat df =new SimpleDateFormat("yyyyMMddHHmmss");
	
	protected Date parseDate(String str) throws Exception {
		if(str == null){
			return null;
		}
		return df.parse(str);
	}
	
	protected double parseDouble(String str) {
		if(str == null){
			return 0;
		}
		return Double.parseDouble(str);
	}
	
	protected int parseInt(String str) {
		if(str == null){
			return 0;
		}
		return Integer.parseInt(str);
	}

	protected String list2String(List<?> lst){
		if(lst.size() <= 0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(Object s : lst){
			if(sb.length() > 0){
				sb.append(",");
			}
			sb.append(s);
		}
		return sb.toString();
	}
	
	protected void str2List(List<String> lst, String value) {
		lst.clear();
		if(value == null){
			return;
		}
		String[] values = value.split(",");
		for(String v : values){
			lst.add(v);
		}
	}
	
	protected void str2IntList(List<Integer> lst, String value) {
		lst.clear();
		if(value == null){
			return;
		}
		String[] values = value.split(",");
		for(String v : values){
			lst.add(Integer.parseInt(v));
		}
	}
}
