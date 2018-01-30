package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shoppingmall.input.QueryParamUser;
import shoppingmall.input.QueryParamUserReview;
import shoppingmall.input.ScrollQueryUserReview;
import shoppingmall.po.User;
import shoppingmall.po.UserAddress;
import shoppingmall.po.UserAuth;
import shoppingmall.po.UserReview;
import shoppingmall.po.UserScore;

public interface DAOUser {

	@Insert({"insert into user()values()"})
	void addUser(User user)throws Exception;

	@Update({"update user set ",
		"where userseq=#{userseq}"})
	void updateUser(User user)throws Exception;

	@Insert({"insert into user_auth(userseq,password)values(#{userseq},#{password})"})
	void addUserAuth(UserAuth auth)throws Exception;

	@Update({"update user_auth set ",
		"where userseq=#{userseq}"})
	void updateUserAuth(UserAuth user)throws Exception;

	@Insert({"insert into user_score(userseq)values(#{userseq})"})
	void addUserScore(UserScore score)throws Exception;

	@Select({"select * from user where userseq=#{value}"})
	User getUserBySeq(Long userseq)throws Exception;

	@Select({"select * from user where nickname=#{nickname}"})
	User getUserByNickname(String nickname)throws Exception;

	@Select({"select * from user where mobileno=#{mobileno}"})
	User getUserByMobileno(String mobileno)throws Exception;

	@Select({"select * from user_review where userseq=#{userseq} ",
		"and orderseq<#{minvalue} order by orderseq limit 0, ${pageSize}"})
	List<UserReview> queryUserReviewList(ScrollQueryUserReview params)throws Exception;

	@Select({"select * from user_auth where userseq=#{value}"})
	UserAuth getUserAuth(Long userseq)throws Exception;

	@Select({"select * from user_addr where userseq=#{value}"})
	List<UserAddress> queryUserAddressList(long userseq)throws Exception;

	@Select({"select * from user_addr where addrno=#{value}"})
	UserAddress getUserAddress(long addrno)throws Exception;
	
	/*-- admin --*/

	@Select({"select * from user_auth where "})
	List<UserAuth> queryUserAuthList(QueryParamUser params)throws Exception;

	@Select({"select * from user where "})
	List<User> queryUserList(QueryParamUser params)throws Exception;

	@Select({"select * from user_review where "})
	List<UserReview> queryUserReviewListByAdmin(QueryParamUserReview params)throws Exception;

}
