package shoppingmall.input;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class ScrollQueryUserReview extends ScrollQuery {
	private long userseq;

	public long getUserseq() {
		return userseq;
	}

	public void setUserseq(long userseq) {
		this.userseq = userseq;
	}
}
