package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shoppingmall.input.ParamGoods;
import shoppingmall.input.QueryParamGoods;
import shoppingmall.input.QueryParamGoodsReview;
import shoppingmall.input.ScrollQueryGoods;
import shoppingmall.input.ScrollQueryGoodsReview;
import shoppingmall.po.Goods;
import shoppingmall.po.GoodsKind;
import shoppingmall.po.GoodsReview;
import shoppingmall.po.GoodsSort;

public interface DAOGoods {

	@Select({"select max(goodsseq) from goods"})
	Long getMaxGoodsseq()throws Exception;

	@Insert({"insert into goods()values()"})
	void addGoods(Goods goods)throws Exception;

	@Update({"update goods set status=#{status} where goodsseq=#{goodsseq}"})
	void updateGoodsStatus(Goods goods)throws Exception;

	@Update({"update goods set ",
		"where goodsseq=#{goodsseq}"})
	void updateGoods(ParamGoods goods)throws Exception;

	@Update({"update goods set refreshtime=sysdate() where goodsseq=#{goodsseq}"})
	void refreshGoods(Goods goods)throws Exception;

	@Select({"select * from goods where goodsseq=#{value}"})
	Goods getGoods(long goodsseq)throws Exception;

	@Select({"select a.* from goods a where a.status='0' and parentseq='0'",
		"left join goods_sort b on a.goodsseq=b.goodsseq",
		"b.ord <#{minvalue}",
		"order by b.ord desc limit 0, ${pageSize}"})
	List<Goods> queryGoodsList(ScrollQueryGoods params)throws Exception;

	@Select({"select * from goods_kind order by ord"})
	List<GoodsKind> queryGoodsKindList()throws Exception;

	@Select({"select * from goods_review where goodsseq=#{goodsseq} and orderseq<#{minvalue}",
		"order by orderseq desc limit 0, ${pageSize}"})
	List<GoodsReview> queryGoodsReviewList(ScrollQueryGoodsReview params)throws Exception;

	@Select({"select * from goods where status='0' and (goodsseq=#{value} or parentseq=#{value})"})
	List<Goods> queryGoodsItemList(long goodsseq)throws Exception;

	@Select({"select * from goods_sort where goodsseq=#{value}"})
	GoodsSort getGoodsSort(long goodsseq)throws Exception;

	
	/*-- admin --*/
	
	@Select({"<script>select a.* from goods a",
		"left join goods_sort b on a.goodsseq=b.goodsseq where 1=1",
		"<if test='goodsname!=null'>and a.goodsname like '%${goodsname}%'</if>",
		"<if test='kind!=null'>and a.kind=#{kind}</if>",
		"<if test='status!=null'>and a.status=#{status}</if>",
		"<if test='goodsseq!=0'>and a.goodsseq=#{goodsseq}</if>",
		"<if test='begindate!=null'>and a.createtime &gt;=#{begindate}</if>",
		"<if test='enddate!=null'>and a.createtime &lt;=#{enddate}</if>",
		"<if test='next!=null'>and b.ord &lt; #{minseq}</if>",
		"<if test='prev!=null'>and b.ord &gt; #{maxseq}</if>",
		"order by b.ord desc limit 0, ${pageSize}</script>"})
	List<Goods> queryGoodsListByAdmin(QueryParamGoods params)throws Exception;

	@Select({"<script>select * from goods_review where 1=1",
		"<if test='keyword!=null'>and review like '%${keyword}%'</if>",
		"<if test='sellerseq!=0'>and sellerseq=#{sellerseq}</if>",
		"<if test='buyerseq!=0'>and userseq=#{buyerseq}</if>",
		"<if test='begindate!=null'>and createtime &gt;=#{begindate}</if>",
		"<if test='enddate!=null'>and createtime &lt;=#{enddate}</if>",
		"<if test='next!=null'>and concat(orderseq,goodsseq) &lt; #{minseq}</if>",
		"<if test='prev!=null'>and concat(orderseq,goodsseq) &gt; #{maxseq}</if>",
		"order by concat(orderseq,goodsseq) desc limit 0, ${pageSize}</script>"})
	List<GoodsReview> queryGoodsReviewListByAdmin(QueryParamGoodsReview params)throws Exception;

}
