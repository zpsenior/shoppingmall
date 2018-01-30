package shoppingmall.input;

import java.util.Date;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class QueryParamMessage extends QueryParam {
	
	private Long userseq;
	private Date begindate;
	private Date enddate;

	public Long getUserseq() {
		return userseq;
	}

	public void setUserseq(Long userseq) {
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

}
