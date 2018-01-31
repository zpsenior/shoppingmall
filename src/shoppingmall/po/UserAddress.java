package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class UserAddress extends POBase {
	
	private long userseq;
	private long addrno;
	private String recipients;
	private String addr;
	private String nation;
	private String province;
	private String city;
	private String mobileno;
	private boolean defaultaddr;
	
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
	public long getAddrno() {
		return addrno;
	}
	public void setAddrno(long addrno) {
		this.addrno = addrno;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public boolean isDefaultaddr() {
		return defaultaddr;
	}
	public void setDefaultaddr(boolean defaultaddr) {
		this.defaultaddr = defaultaddr;
	}
}
