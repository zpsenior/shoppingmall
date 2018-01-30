package shoppingmall.input;

import graphql4j.annotation.GraphQLInput;
import shoppingmall.po.User;

@GraphQLInput
public class ParamUser extends User {
	private String checkcode;
	private String password;
	private String confirmpwd;

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpwd() {
		return confirmpwd;
	}

	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
}
