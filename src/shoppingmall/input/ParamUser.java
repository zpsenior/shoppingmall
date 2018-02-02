package shoppingmall.input;

import graphql4j.annotation.GraphQLInput;
import shoppingmall.po.User;

@GraphQLInput
public class ParamUser extends User {

	private static final long serialVersionUID = -4057668964770353966L;
	
	private String checkcode;
	private String oldpwd;
	private String password;
	private String confirmpwd;

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
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
