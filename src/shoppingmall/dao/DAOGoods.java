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
	
	@Select({"select * from goods where"})
	List<Goods> queryGoodsListByAdmin(QueryParamGoods params)throws Exception;

	@Select({"select * from goods_review where"})
	List<GoodsReview> queryGoodsReviewListByAdmin(QueryParamGoodsReview params)throws Exception;

}
