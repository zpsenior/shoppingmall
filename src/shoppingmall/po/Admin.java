package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class Admin extends POBase {
	
	private long adminseq;
	private String adminname;
	private String mobileno;
	private long adminrole;
	private String password;
	private String status;
	public long getAdminseq() {
		return adminseq;
	}
	public void setAdminseq(long adminseq) {
		this.adminseq = adminseq;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public long getAdminrole() {
		return adminrole;
	}
	public void setAdminrole(long adminrole) {
		this.adminrole = adminrole;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
