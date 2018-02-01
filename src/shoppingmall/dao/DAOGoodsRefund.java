package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shoppingmall.input.QueryParamGoodsRefund;
import shoppingmall.input.ScrollQueryGoodsRefund;
import shoppingmall.po.GoodsOrderRefund;

public interface DAOGoodsRefund {
	

	@Select({"select max(refundseq) from goods_order_refund"})
	Long getMaxRefundseq()throws Exception;

	@Select({"select max(refundno) from goods_order_refund where orderseq=#{orderseq} and goodsseq=#{goodsseq}"})
	Integer getRefundCount(GoodsOrderRefund refund)throws Exception;
	
	@Insert({"insert into goods_order_refund(",
		"refundseq,",
		"orderseq,",
		"goodsseq,",
		"refundno,",
		"buyerseq,",
		"sellerseq,",
		"reason,",
		"imgs,",
		"amount,",
		"count,",
		"createtime)values(",
		"#{refundseq},",
		"#{orderseq},",
		"#{goodsseq},",
		"#{refundno},",
		"#{buyerseq},",
		"#{sellerseq},",
		"#{reason},",
		"#{imgs},",
		"#{amount},",
		"#{count},",
		"sysdate())"})
	void addGoodsRefund(GoodsOrderRefund ref)throws Exception;
	
	@Update({"update goods_order_refund set status='6' where refundseq=#{refundseq}"})
	void cancelGoodsRefund(GoodsOrderRefund refund)throws Exception;

	@Update({"update goods_order_refund set status='5' where refundseq=#{refundseq}"})
	void applyArbitration(GoodsOrderRefund refund)throws Exception;

	@Update({"update goods_order_refund set status='2',refusereason=#{refusereason} where refundseq=#{refundseq}"})
	void refuseGoodsRefund(GoodsOrderRefund refund)throws Exception;
	
	@Update({"update goods_order_refund set status='3' where refundseq=#{refundseq}"})
	void acceptGoodsRefund(GoodsOrderRefund refund)throws Exception;

	@Update({"update goods_order_refund set postorder=#{postorder}, postcomp=#{postcomp} where refundseq=#{refundseq}"})
	void fillRefundPost(GoodsOrderRefund refund)throws Exception;

	@Update({"update goods_order_refund set status='4', paychannel=#{paychannel}, payid=#{payid}, refundtime=sysdate() where refundseq=#{refundseq}"})
	void confirmRefund(GoodsOrderRefund refund)throws Exception;
	
	
	

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
