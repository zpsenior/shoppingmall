package shoppingmall.input;

import java.util.Date;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class ScrollQueryUserBalance extends ScrollQuery {
	private long userseq;
	private Date begindate;
	private Date enddate;
	private String type;
	
	public long getUserseq() {
		return userseq;
	}
	public void setUserseq(long userseq) {
		this.userseq = userseq;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
