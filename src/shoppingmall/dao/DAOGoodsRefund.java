package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import shoppingmall.input.QueryParamGoodsRefund;
import shoppingmall.input.ScrollQueryGoodsRefund;
import shoppingmall.po.GoodsOrderRefund;

public interface DAOGoodsRefund {

	@Select({"select * from goods_order_refund where refundseq=#{value}"})
	GoodsOrderRefund getGoodsRefund(long refundseq)throws Exception;

	@Select({"select * from goods_order_refund where buyerseq=#{buyerseq}",
		"and refundseq<#{minvalue} order by refundseq desc limit 0, ${pageSize}"})
	List<GoodsOrderRefund> queryGoodsRefundList(ScrollQueryGoodsRefund params)throws Exception;
	
	/*-- admin --*/

	@Select({"select * from goods_order_refund where "})
	List<GoodsOrderRefund> queryGoodsRefundListByAdmin(QueryParamGoodsRefund params)throws Exception;

}
