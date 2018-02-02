package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.io.Serializable;
import java.util.Date;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class User extends POBase implements Serializable {

	private static final long serialVersionUID = 8471939739912904324L;
	
	public final static String STATUS_OK     = "0";
	public final static String STATUS_SHIELD = "2";

	private long   userseq;
	private String loginname;
	private String nationcode;
	private String mobileno;
	private String nickname;
	private String headpoto;
	private String openid;
	
	private String status;
	private Date lastlogin;
	
	private String platform;
	private String device;
	
	private String nation;
	private String province;
	private String city;
	
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getNationcode() {
		return nationcode;
	}
	public void setNationcode(String nationcode) {
		this.nationcode = nationcode;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadpoto() {
		return headpoto;
	}
	public void setHeadpoto(String headpoto) {
		this.headpoto = headpoto;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
