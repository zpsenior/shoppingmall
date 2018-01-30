package shoppingmall.po;

import graphql4j.annotation.GraphQLObject;

import java.util.Date;
import java.util.List;

import shoppingmall.pub.POBase;

@GraphQLObject(valueObject=true)
public class Goods extends POBase {
	
	public static final String STATUS_OK = "0";
	public static final String STATUS_OFFLINE = "1";
	public static final String STATUS_REMOVE = "2";
	
	public static final Long MIN_GOODS_SEQ = 48899L;
	
	private long goodsseq;
	private long parentseq;
	private String goodsname;
	private String descript;
	private String coverpath;
	private String videopath;
	private int price;
	private int postfee;
	private String goodskind;
	private long sellerseq;
	private String status;
	private int minbuycount;
	private int maxbuycount;
	private int maxcount;
	private Date begindate;
	private Date enddate;
	
	public long getGoodsseq() {
		return goodsseq;
	}
	public void setGoodsseq(long goodsseq) {
		this.goodsseq = goodsseq;
	}
	public long getParentseq() {
		return parentseq;
	}
	public void setParentseq(long parentseq) {
		this.parentseq = parentseq;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getCoverpath() {
		return coverpath;
	}
	public void setCoverpath(String coverpath) {
		this.coverpath = coverpath;
	}
	public String getVideopath() {
		return videopath;
	}
	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPostfee() {
		return postfee;
	}
	public void setPostfee(int postfee) {
		this.postfee = postfee;
	}
	public String getGoodskind() {
		return goodskind;
	}
	public void setGoodskind(String goodskind) {
		this.goodskind = goodskind;
	}
	public long getSellerseq() {
		return sellerseq;
	}
	public void setSellerseq(long sellerseq) {
		this.sellerseq = sellerseq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMinbuycount() {
		return minbuycount;
	}
	public void setMinbuycount(int minbuycount) {
		this.minbuycount = minbuycount;
	}
	public int getMaxbuycount() {
		return maxbuycount;
	}
	public void setMaxbuycount(int maxbuycount) {
		this.maxbuycount = maxbuycount;
	}
	public int getMaxcount() {
		return maxcount;
	}
	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
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
	
	public User getSeller()throws Exception{
		User user = getEnvironment().getDAOUser().getUserBySeq(sellerseq);
		user.bindEnvironment(getEnvironment());
		return user;
	}
	
	public List<Goods> getItems()throws Exception{
		List<Goods> list = getEnvironment().getDAOGoods().queryGoodsItemList(goodsseq);
		bindEnvironment(list);
		return list;
	}
	
	public GoodsSort getGoodsSort()throws Exception{
		GoodsSort sort = getEnvironment().getDAOGoods().getGoodsSort(goodsseq);
		sort.bindEnvironment(getEnvironment());
		return sort;
	}
}
