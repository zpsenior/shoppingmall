package shoppingmall.app.bo;

import shoppingmall.dao.DAOGoods;
import shoppingmall.exception.ValidationException;
import shoppingmall.input.ParamGoods;
import shoppingmall.po.Goods;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoods")
public class AppMutationGoods extends BOBase {
	
	public AppMutationGoods(Environment env) {
		super(env);
	}

	@GraphQLField("publish")
	public boolean publish(@GraphQLArgument("goods") ParamGoods goods)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		long seq = nextGoodsseq(daoGoods);
		goods.setGoodsseq(seq);
		daoGoods.addGoods(goods);
		return true;
	}
	
	private long nextGoodsseq(DAOGoods daoGoods)throws Exception{
		Long key = daoGoods.getMaxGoodsseq();
		if(key == null){
			key = Goods.MIN_GOODS_SEQ;
		}
		key++;
		return key;
	}

	@GraphQLField("online")
	public boolean online(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		Goods goods = daoGoods.getGoods(goodsseq);
		if(goods == null){
			throw new ValidationException("not.exist.goods", goodsseq);
		}
		if(!Goods.STATUS_OFFLINE.equals(goods.getStatus())){
			throw new ValidationException("goods.status.is.not.offline", goodsseq);
		}
		goods.setStatus(Goods.STATUS_OK);
		daoGoods.updateGoodsStatus(goods);
		return true;
	}
	
	@GraphQLField("offline")
	public boolean offline(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		Goods goods = daoGoods.getGoods(goodsseq);
		if(goods == null){
			throw new ValidationException("not.exist.goods", goodsseq);
		}
		if(!Goods.STATUS_OK.equals(goods.getStatus())){
			throw new ValidationException("goods.status.is.not.online", goodsseq);
		}
		goods.setStatus(Goods.STATUS_OFFLINE);
		daoGoods.updateGoodsStatus(goods);
		return true;
	}
	
	@GraphQLField("remove")
	public boolean remove(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		Goods goods = daoGoods.getGoods(goodsseq);
		if(goods == null){
			throw new ValidationException("not.exist.goods", goodsseq);
		}
		if(!Goods.STATUS_OFFLINE.equals(goods.getStatus())){
			throw new ValidationException("goods.status.is.not.offline", goodsseq);
		}
		goods.setStatus(Goods.STATUS_REMOVE);
		daoGoods.updateGoodsStatus(goods);
		return true;
	}
	
	@GraphQLField("modify")
	public boolean modify(@GraphQLArgument("goods") ParamGoods goods)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		long goodsseq = goods.getGoodsseq();
		if(daoGoods.getGoods(goodsseq) == null){
			throw new ValidationException("not.exist.goods", goodsseq);
		}
		daoGoods.updateGoods(goods);
		return true;
	}
	
	@GraphQLField("refresh")
	public boolean refresh(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		DAOGoods daoGoods = getDAO().getDAOGoods();
		Goods goods = daoGoods.getGoods(goodsseq);
		if(goods == null){
			throw new ValidationException("not.exist.goods", goodsseq);
		}
		daoGoods.refreshGoods(goods);
		return true;
	}

}
