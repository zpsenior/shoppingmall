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

	@Select({"<script>select * from user_auth where 1=1",
		"<if test='truename!=null'>and truename like '%${truename}%'</if>",
		"<if test='username!=null'>and username like '%${username}%'</if>",
		"<if test='next!=null'>and userseq &lt; #{minseq}</if>",
		"<if test='prev!=null'>and userseq &gt; #{maxseq}</if>",
		"order by userseq desc limit 0, ${pageSize}</script>"})
	List<UserAuth> queryUserAuthList(QueryParamUser params)throws Exception;

	@Select({"<script>select * from user where 1=1",
		"<if test='nickname!=null'>and nickname like '%${nickname}%'</if>",
		"<if test='status!=null'>and status=#{status}</if>",
		"<if test='certify!=null'>and certify=#{certify}</if>",
		"<if test='nation!=null'>and nation=#{nation}</if>",
		"<if test='province!=null'>and province=#{province}</if>",
		"<if test='city!=null'>and city=#{city}</if>",
		"<if test='mobileno!=null'>and mobileno like '%${mobileno}%'</if>",
		"<if test='next!=null'>and userseq &lt; #{minseq}</if>",
		"<if test='prev!=null'>and userseq &gt; #{maxseq}</if>",
		"order by userseq desc limit 0, ${pageSize}</script>"})
	List<User> queryUserList(QueryParamUser params)throws Exception;

	@Select({"<script>select * from user_review where 1=1",
		"order by msgseq desc limit 0, ${pageSize}</script>"})
	List<UserReview> queryUserReviewListByAdmin(QueryParamUserReview params)throws Exception;

}
