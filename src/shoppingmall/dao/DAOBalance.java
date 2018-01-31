package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import shoppingmall.input.ScrollQueryUserBalance;
import shoppingmall.po.UserBalance;

public interface DAOBalance {

	void addUserBalance(UserBalance balance)throws Exception;

	UserBalance getLastUserBalance(long userseq)throws Exception;

	Long getMaxBalanceseq()throws Exception;

	@Select({"select * from user_balance where balseq=#{value}"})
	UserBalance getUserBalance(long balseq)throws Exception;

	@Select({"select * from user_balance where userseq=#{userseq} and balseq<#{minvalue} order by balseq desc limit 0, ${pageSize}"})
	List<UserBalance> queryUserBalanceList(ScrollQueryUserBalance params)throws Exception;

}
