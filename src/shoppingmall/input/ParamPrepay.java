package shoppingmall.input;

import graphql4j.annotation.GraphQLInput;

@GraphQLInput
public class ParamPrepay {
	private long[] goodsseqs;
	private int addrno;
	private String postkind;
	private int postfee;
	private String channel;
	private String IP = "8.8.8.8";
	public long[] getGoodsseqs() {
		return goodsseqs;
	}
	public void setGoodsseqs(long[] goodsseqs) {
		this.goodsseqs = goodsseqs;
	}
	public int getAddrno() {
		return addrno;
	}
	public void setAddrno(int addrno) {
		this.addrno = addrno;
	}
	public String getPostkind() {
		return postkind;
	}
	public void setPostkind(String postkind) {
		this.postkind = postkind;
	}
	public int getPostfee() {
		return postfee;
	}
	public void setPostfee(int postfee) {
		this.postfee = postfee;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
}
