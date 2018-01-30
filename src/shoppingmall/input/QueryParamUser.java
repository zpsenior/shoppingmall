package shoppingmall.input;

import java.util.Date;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class QueryParamUser extends QueryParam {
	
	private Long userseq;
	private String nickname;
	private Date begindate;
	private Date enddate;
	private String status;
	private String mobileno;
	private String residence;
	
	private String certify;

	public Long getUserseq() {
		return userseq;
	}

	public void setUserseq(Long userseq) {
		this.userseq = userseq;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getCertify() {
		return certify;
	}

	public void setCertify(String certify) {
		this.certify = certify;
	}
}
