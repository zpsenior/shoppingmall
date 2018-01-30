package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;
import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class SysMessage extends POBase {
	private long msgseq;
	private long touserseq;
	private String kind;
	private String title;
	private String content;
	private String status;
	public long getMsgseq() {
		return msgseq;
	}
	public void setMsgseq(long msgseq) {
		this.msgseq = msgseq;
	}
	public long getTouserseq() {
		return touserseq;
	}
	public void setTouserseq(long touserseq) {
		this.touserseq = touserseq;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
