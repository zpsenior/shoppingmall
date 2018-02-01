package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shoppingmall.po.GoodsReview;
import shoppingmall.input.QueryParamGoodsOrder;
import shoppingmall.input.ScrollQueryGoodsOrder;
import shoppingmall.po.GoodsOrder;
import shoppingmall.po.GoodsOrderItem;
import shoppingmall.po.GoodsShoppingCart;

public interface DAOGoodsOrder {

	@Insert({"insert into goods_order(",
		"orderseq,",
		"buyerseq,",
		"title,",
		"total,",
		"postkind,",
		"postfee,",
		"recipients,",
		"nation,",
		"province,",
		"city,",
		"addr,",
		"mobileno,",
		"createtime)values(",
		"#{orderseq},",
		"#{buyerseq},",
		"#{title},",
		"#{total},",
		"#{postkind},",
		"#{postfee},",
		"#{recipients},",
		"#{nation},",
		"#{province},",
		"#{city},",
		"#{addr},",
		"#{mobileno},",
		"sysdate())"})
	void addGoodsOrder(GoodsOrder order)throws Exception;

	@Update({"update goods_order set status='1',",
		"paychannel=#{paychannel},",
		"payid=#{payid},",
		"paytime=#{paytime} ",
		"where orderseq=#{orderseq}"})
	void modifyGoodsOrderPayed(GoodsOrder order)throws Exception;

	@Update({"update goods_order set status='4', refusereason=#{refusereason} where orderseq=#{orderseq}"})
	void refuseGoodsOrder(GoodsOrder order)throws Exception;
	
	@Update({"update goods_order set status='3' where orderseq in (${value})"})
	void closeGoodsOrder(String seqs)throws Exception;

	@Update({"update goods_order set hide=#{hide} where orderseq=#{orderseq}"})
	void removeTPGoodsOrder(GoodsOrder order)throws Exception;
	
	@Update({"update goods_order set status='2',",
		"postorder=#{postorder},",
		"postcomp=#{postcomp},",
		"deliverytime=sysdate()",
		"where orderseq=#{orderseq}"})
	void deliveryGoodsOrder(GoodsOrder order)throws Exception;
	
	@Update({"update goods_order set status='5',",
		"finishtime=sysdate()",
		"where orderseq in (#{value})"})
	void confirmGoodsOrder(GoodsOrder order)throws Exception;
	
	@Insert({"insert into goods_order_item(",
		"orderseq,",
		"goodsseq,",
		"goodsname,",
		"sellerseq,",
		"count,",
		"price,",
		"createtime)values(",
		"#{orderseq},",
		"#{goodsseq},",
		"#{goodsname},",
		"#{sellerseq},",
		"#{count},",
		"#{price},",
		"sysdate())"})
	void addGoodsOrderItem(GoodsOrderItem item)throws Exception;

	@Select({"select * from goods_shopping_cart where userseq=#{value}"})
	List<GoodsShoppingCart> queryGoodsShoppingCartList(long userseq)throws Exception;

	@Select({"select * from goods_order_item where userseq=#{sellerseq} and (hide='0' or hide='2')",
		"and orderseq<#{minvalue} order by orderseq desc limit 0, ${pageSize}"})
	List<GoodsOrderItem> queryGoodsSaleList(ScrollQueryGoodsOrder params)throws Exception;

	@Select({"select max(orderseq) from goods_order"})
	Long getMaxOrderseq()throws Exception;

	@Select({"select * from goods_order where orderseq=#{value}"})
	GoodsOrder getGoodsOrder(long orderseq)throws Exception;


	@Select({"select * from goods_order where userseq=#{buyerseq} and (hide='0' or hide='1')",
		"and orderseq<#{minvalue} order by orderseq desc limit 0, ${pageSize}"})
	List<GoodsOrder> queryGoodsOrderList(ScrollQueryGoodsOrder params)throws Exception;
	
	@Select({"select * from goods_order_item where orderseq=#{value}"})
	List<GoodsOrderItem> queryGoodsOrderItemList(long orderseq)throws Exception;

	@Insert({"insert into goods_review(",
		"orderseq,",
		"goodsseq,",
		"buyerseq,",
		"userseq,",
		"score,",
		"review,",
		"imgs,",
		"createtime)values(",
		"#{orderseq},",
		"#{goodsseq},",
		"#{buyerseq},",
		"#{userseq},",
		"#{score},",
		"#{review},",
		"#{imgs},",
		"sysdate())"})
	void addGoodsReview(GoodsReview review)throws Exception;
	
	
	/*-- admin --*/
	
	@Select({"<script>select * from goods_order where 1=1",
		"<if test='buyerseq!=0'>and buyerseq=#{buyerseq}</if>",
		"<if test='begindate!=null'>and createtime &gt;=#{begindate}</if>",
		"<if test='enddate!=null'>and createtime &lt;=#{enddate}</if>",
		"<if test='next!=null'>and orderseq &lt; #{minseq}</if>",
		"<if test='prev!=null'>and orderseq &gt; #{maxseq}</if>",
		"order by orderseq desc limit 0, ${pageSize}</script>"})
	List<GoodsOrder> queryGoodsOrderListByAdmin(QueryParamGoodsOrder params)throws Exception;

	
	@Select({"<script>select * from goods_order_item where 1=1",
		"<if test='goodsname!=null'>and goodsname like '%${goodsname}%'</if>",
		"<if test='goodsseq!=0'>and goodsseq=#{goodsseq}</if>",
		"<if test='sellerseq!=0'>and sellerseq=#{sellerseq}</if>",
		"<if test='next!=null'>and concat(orderseq,goodsseq) &lt; #{minseq}</if>",
		"<if test='prev!=null'>and concat(orderseq,goodsseq) &gt; #{maxseq}</if>",
		"order by concat(orderseq,goodsseq) desc limit 0, ${pageSize}</script>"})
	List<GoodsOrderItem> queryGoodsSaleListByAdmin(QueryParamGoodsOrder params)throws Exception;

}
