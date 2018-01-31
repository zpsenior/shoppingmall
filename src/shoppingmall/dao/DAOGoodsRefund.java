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


	@Select({"select * from goods_order_refund where orderseq=#{value} order by refundseq desc"})
	List<GoodsOrderRefund> queryGoodsRefundListByOrder(long orderseq)throws Exception;
	
	/*-- admin --*/

	@Select({"<script>select * from goods_order_refund where 1=1",
		"<if test='goodsseq!=0'>and goodsseq=#{goodsseq}</if>",
		"<if test='buyerseq!=0'>and buyerseq=#{buyerseq}</if>",
		"<if test='sellerseq!=0'>and sellerseq=#{sellerseq}</if>",
		"<if test='next!=null'>and refundseq &lt; #{minseq}</if>",
		"<if test='prev!=null'>and refundseq &gt; #{maxseq}</if>",
		"order by refundseq desc limit 0, ${pageSize}</script>"})
	List<GoodsOrderRefund> queryGoodsRefundListByAdmin(QueryParamGoodsRefund params)throws Exception;

}
