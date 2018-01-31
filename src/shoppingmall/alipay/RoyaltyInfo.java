package shoppingmall.alipay;

import java.util.List;

public class RoyaltyInfo{
	private String royalty_type;
	private List<RoyaltyDetailInfo> royalty_detail_infos;
	public String getRoyalty_type() {
		return royalty_type;
	}
	public void setRoyalty_type(String royalty_type) {
		this.royalty_type = royalty_type;
	}
	public List<RoyaltyDetailInfo> getRoyalty_detail_infos() {
		return royalty_detail_infos;
	}
	public void setRoyalty_detail_infos(List<RoyaltyDetailInfo> royalty_detail_infos) {
		this.royalty_detail_infos = royalty_detail_infos;
	}
	
}
