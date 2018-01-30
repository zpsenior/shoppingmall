package shoppingmall.input;

import java.util.Date;

public class QueryParamGoodsOrder extends QueryParam {
	private long sellerseq;
	private long buyerseq;
	private long goodsseq;
	private String goodsname;
	private String status;
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

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
