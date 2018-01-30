package shoppingmall.input;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class ScrollQueryGoods extends ScrollQuery {
	private Long seller;
	private String kind;
	private String keyword;
	private String status;
	
	public Long getSeller() {
		return seller;
	}
	public void setSeller(Long seller) {
		this.seller = seller;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
