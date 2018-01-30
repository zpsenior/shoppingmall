package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import shoppingmall.input.QueryParamGoodsOrder;
import shoppingmall.input.ScrollQueryGoodsOrder;
import shoppingmall.po.GoodsOrder;
import shoppingmall.po.GoodsOrderItem;
import shoppingmall.po.GoodsShoppingCart;

public interface DAOGoodsOrder {

	@Select({"select * from goods_shopping_cart where userseq=#{value}"})
	List<GoodsShoppingCart> queryGoodsShoppingCart(long userseq)throws Exception;

	@Select({"select * from goods_order_item where userseq=#{sellerseq}",
		"and orderseq<#{minvalue} order by orderseq desc limit 0, ${pageSize}"})
	List<GoodsOrderItem> queryGoodsSaleList(ScrollQueryGoodsOrder params)throws Exception;

	@Select({"select * from goods_order where orderseq=#{value}"})
	GoodsOrder getGoodsOrder(long orderseq)throws Exception;


	@Select({"select * from goods_order where userseq=#{buyerseq}",
		"and orderseq<#{minvalue} order by orderseq desc limit 0, ${pageSize}"})
	List<GoodsOrder> queryGoodsOrderList(ScrollQueryGoodsOrder params)throws Exception;
	
	@Select({"select * from goods_order_item where orderseq=#{value}"})
	List<GoodsOrderItem> queryGoodsOrderItemList(long orderseq)throws Exception;
	
	
	/*-- admin --*/
	
	@Select({"select * from goods_order where"})
	List<GoodsOrder> queryGoodsOrderListByAdmin(QueryParamGoodsOrder params)throws Exception;

	
	@Select({"select * from goods_order_item where"})
	List<GoodsOrderItem> queryGoodsSaleListByAdmin(QueryParamGoodsOrder params)throws Exception;

}
