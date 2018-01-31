package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class AdminLog extends POBase {
	
	private long logseq;
	private long adminseq;
	private String adminname;
	private String content;
	public long getLogseq() {
		return logseq;
	}
	public void setLogseq(long logseq) {
		this.logseq = logseq;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
