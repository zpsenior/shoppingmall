package shoppingmall.admin.bo;

import java.util.List;

import shoppingmall.input.QueryParamGoods;
import shoppingmall.input.QueryParamGoodsOrder;
import shoppingmall.input.QueryParamGoodsRefund;
import shoppingmall.input.QueryParamGoodsReview;
import shoppingmall.input.QueryParamMessage;
import shoppingmall.input.QueryParamUser;
import shoppingmall.input.QueryParamUserReview;
import shoppingmall.po.Goods;
import shoppingmall.po.GoodsOrder;
import shoppingmall.po.GoodsOrderItem;
import shoppingmall.po.GoodsOrderRefund;
import shoppingmall.po.GoodsReview;
import shoppingmall.po.SysMessage;
import shoppingmall.po.User;
import shoppingmall.po.UserAuth;
import shoppingmall.po.UserReview;
import shoppingmall.pub.BOBase;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("Query")
public class AdminQuery  extends BOBase {

	@GraphQLField("userAuth")
	public UserAuth getUserAuth(@GraphQLArgument("userseq") long userseq)throws Exception{
		UserAuth auth = getDAO().getDAOUser().getUserAuth(userseq);
		return auth;
	}

	@GraphQLField("userAuths")
	public List<UserAuth> queryUserAuthList(@GraphQLArgument("params") QueryParamUser params)throws Exception{
		List<UserAuth> list = getDAO().getDAOUser().queryUserAuthList(params);
		return list;
	}
	
	@GraphQLField("user")
	public User getUser(@GraphQLArgument("userseq") long userseq)throws Exception{
		User user = getDAO().getDAOUser().getUserBySeq(userseq);
		return user;
	}
	
	@GraphQLField("users")
	public List<User> queryUserList(@GraphQLArgument("params") QueryParamUser params)throws Exception{
		List<User> list = getDAO().getDAOUser().queryUserList(params);
		return list;
	}
	
	@GraphQLField("userReviews")
	public List<UserReview> queryUserReviewList(@GraphQLArgument("params") QueryParamUserReview params)throws Exception{
		List<UserReview> list = getDAO().getDAOUser().queryUserReviewListByAdmin(params);
		return list;
	}
	
	@GraphQLField("goodsList")
	public List<Goods> queryGoodsList(@GraphQLArgument("params") QueryParamGoods params)throws Exception{
		List<Goods> list = getDAO().getDAOGoods().queryGoodsListByAdmin(params);
		return list;
	}
	
	@GraphQLField("goods")
	public Goods getGoods(@GraphQLArgument("goodsseq") long goodsseq)throws Exception{
		Goods goods = getDAO().getDAOGoods().getGoods(goodsseq);
		return goods;
	}
	
	@GraphQLField("goodOrder")
	public GoodsOrder getGoodsOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		GoodsOrder order = getDAO().getDAOGoodsOrder().getGoodsOrder(orderseq);
		return order;
	}
	
	@GraphQLField("goodsOrders")
	public List<GoodsOrder> queryGoodsOrderList(@GraphQLArgument("params") QueryParamGoodsOrder params)throws Exception{
		List<GoodsOrder> list = getDAO().getDAOGoodsOrder().queryGoodsOrderListByAdmin(params);
		return list;
	}
	
	@GraphQLField("goodsRefunds")
	public List<GoodsOrderRefund> queryGoodsRefundList(@GraphQLArgument("params") QueryParamGoodsRefund params)throws Exception{
		List<GoodsOrderRefund> list = getDAO().getDAOGoodsRefund().queryGoodsRefundListByAdmin(params);
		return list;
	}
	
	@GraphQLField("goodsReviews")
	public List<GoodsReview> queryGoodsReviewList(@GraphQLArgument("params") QueryParamGoodsReview params)throws Exception{
		List<GoodsReview> list = getDAO().getDAOGoods().queryGoodsReviewListByAdmin(params);
		return list;
	}
	
	@GraphQLField("sysMessage")
	public List<SysMessage> querySysMessageList(@GraphQLArgument("params") QueryParamMessage params)throws Exception{
		List<SysMessage> list = getDAO().getDAOSysMessage().querySysMessageListByAdmin(params);
		return list;
	}
	
	@GraphQLField("goodsSales")
	public List<GoodsOrderItem> queryGoodsSaleList(@GraphQLArgument("params") QueryParamGoodsOrder params)throws Exception{
		List<GoodsOrderItem> list = getDAO().getDAOGoodsOrder().queryGoodsSaleListByAdmin(params);
		return list;
	}
}
