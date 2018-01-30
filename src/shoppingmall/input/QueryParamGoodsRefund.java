package shoppingmall.input;

import java.util.Date;

public class QueryParamGoodsRefund extends QueryParam {

	private long sellerseq;
	private long buyerseq;
	private long goodsseq;
	private Date begindate;
	private Date enddate;
	public long getSellerseq() {
		return sellerseq;
	}
	public void setSellerseq(long sellerseq) {
		this.sellerseq = sellerseq;
	}
	public long getBuyerseq() {
		return buyerseq;
	}
	public void setBuyerseq(long buyerseq) {
		this.buyerseq = buyerseq;
	}
	public long getGoodsseq() {
		return goodsseq;
	}
	public void setGoodsseq(long goodsseq) {
		this.goodsseq = goodsseq;
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
