package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class UserAuth extends POBase {
	
	private long   userseq;
	
	private String password;
	
	private String truename;
	private String idtype;
	private String idno;

	private String backchannel;
	private String username;
	private String bankname;
	private String cardno;
	
	private String alipay;
	private String wpay;
	
	private String certify;
	private Date certifytime;
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getBackchannel() {
		return backchannel;
	}
	public void setBackchannel(String backchannel) {
		this.backchannel = backchannel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public String getWpay() {
		return wpay;
	}
	public void setWpay(String wpay) {
		this.wpay = wpay;
	}
	public String getCertify() {
		return certify;
	}
	public void setCertify(String certify) {
		this.certify = certify;
	}
	public Date getCertifytime() {
		return certifytime;
	}
	public void setCertifytime(Date certifytime) {
		this.certifytime = certifytime;
	}
}
