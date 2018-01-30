package shoppingmall.app.bo;

import shoppingmall.input.ParamGoodsReview;
import shoppingmall.input.ParamUserReview;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MGoodsOrder")
public class AppMutationGoodsOrder extends BOBase {
	
	public AppMutationGoodsOrder(Environment env) {
		super(env);
	}

	@GraphQLField("prepay")
	public boolean prepay(@GraphQLArgument("goodsseq") long goodsseq, @GraphQLArgument("addrno") long addrno, @GraphQLArgument("channel") String channel)throws Exception{
		return false;
	}
	
	@GraphQLField("payAgain")
	public boolean payAgain(@GraphQLArgument("orderseq") long goodsseq, @GraphQLArgument("channel") String channel)throws Exception{
		return false;
	}
	
	@GraphQLField("checkOrderPayed")
	public boolean checkPayed(@GraphQLArgument("outTradeNo") String outTradeNo, @GraphQLArgument("channel") String channel)throws Exception{
		return false;
	}

	@GraphQLField("updateOrderPrice")
	public boolean updateOrderPrice(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("price") int price, @GraphQLArgument("postfee") int postfee)throws Exception{
		return false;
	}
	
	@GraphQLField("refuseOrder")
	public boolean refuseOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		return false;
	}
	
	@GraphQLField("closeOrder")
	public boolean closeOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		return false;
	}
	
	@GraphQLField("removeOrder")
	public boolean removeOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		return false;
	}
	
	@GraphQLField("applyArbitration")
	public boolean applyArbitration(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		return false;
	}
	
	@GraphQLField("deliveryOrder")
	public boolean deliveryOrder(@GraphQLArgument("orderseq") long orderseq, @GraphQLArgument("postorder") String postorder, @GraphQLArgument("postcomp") String postcomp)throws Exception{
		return false;
	}
	
	@GraphQLField("confirmOrder")
	public boolean confirmOrder(@GraphQLArgument("orderseq") long orderseq)throws Exception{
		return false;
	}
	
	@GraphQLField("reviewGoods")
	public boolean reviewGoods(@GraphQLArgument("review") ParamGoodsReview review)throws Exception{
		return false;
	}
	
	@GraphQLField("reviewBuyer")
	public boolean reviewBuyer(@GraphQLArgument("review") ParamUserReview review)throws Exception{
		return false;
	}

}
