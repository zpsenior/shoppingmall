package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shoppingmall.input.ScrollQueryUserBalance;
import shoppingmall.po.UserBalance;

public interface DAOBalance {

	@Insert({"insert into user_balance(",
		 "balseq,",
		 "userseq,",
		 "type,",
		 "seq,",
		 "descript,",
		 "channel,",
		 "payid,",
		 "amount,",
		 "balance,",
		 "paytime,",
	"createtime)values(",
		 "#{balseq},",
		 "#{userseq},",
		 "#{type},",
		 "#{seq},",
		 "#{descript},",
		 "#{channel},",
		 "#{payid},",
		 "#{amount},",
		 "#{balance},",
		 "#{paytime},",
	"sysdate())"})
	void addUserBalance(UserBalance balance)throws Exception;

	@Update({"update user_balance set paytime=#{paytime} where balseq=#{balseq}"})
	void updatePaytime(UserBalance balance)throws Exception;

	@Select({"select * from user_balance where balseq in (select max(balseq) from user_balance where userseq=#{value})"})
	UserBalance getLastUserBalance(long userseq)throws Exception;

	@Select({"select max(balseq) from user_balance"})
	Long getMaxBalanceseq()throws Exception;

	@Select({"select * from user_balance where balseq=#{value}"})
	UserBalance getUserBalance(long balseq)throws Exception;

	@Select({"select * from user_balance where userseq=#{userseq} and balseq<#{minvalue} order by balseq desc limit 0, ${pageSize}"})
	List<UserBalance> queryUserBalanceList(ScrollQueryUserBalance params)throws Exception;

}
