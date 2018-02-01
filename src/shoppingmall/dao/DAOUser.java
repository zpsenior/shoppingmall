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

	@Insert({"insert into user(",
		"userseq,",
	 	"loginname,",
	 	"nationcode,",
	 	"mobileno,",
	 	"openid,",
	 	"nickname,",
	 	"headpoto,",
	 	"nation,",
	 	"province,",
	 	"city,",
	 	"platform,",
	 	"device,",
	 	"createtime)values(",
	 	"#{userseq},",
	 	"#{loginname},",
	 	"#{nationcode},",
	 	"#{mobileno},",
	 	"#{openid},",
	 	"#{nickname},",
	 	"#{headpoto},",
	 	"#{nation},",
	 	"#{province},",
	 	"#{city},",
	 	"#{platform},",
	 	"#{device},",
 		"sysdate())"})
	void addUser(User user)throws Exception;

	@Update({"<script>update user set ",
	 	"<if test='loginname!=null'>loginname=#{loginname},</if>",
	 	"<if test='nickname!=null'>nickname=#{nickname},</if>",
	 	"<if test='nation!=null'>nation=#{nation},</if>",
	 	"<if test='province!=null'>province=#{province},</if>",
	 	"<if test='city!=null'>city=#{city},</if>",
	 	"<if test='headpoto!=null'>headpoto=#{headpoto},</if>",
	 	"<if test='platform!=null'>platform=#{platform},</if>",
	 	"<if test='device!=null'>device=#{device},</if>",
	 	"lastlogin=sysdate()",
		"where userseq=#{userseq}</script>"})
	void updateUser(User user)throws Exception;
	
	@Update({"update user set openid=#{openid} where userseq=#{userseq}"})
	void addOpenidInUser(User user)throws Exception;

	@Insert({"insert into user_auth(userseq,password,updatetime)values(#{userseq},#{password},sysdate())"})
	void addUserAuth(UserAuth auth)throws Exception;

	@Update({"<script>update user_auth set ",
		"<if test='backchannel!=null'>backchannel=#{backchannel},</if>",
		"<if test='bankname!=null'>bankname=#{bankname},</if>",
		"<if test='username!=null'>username=#{username},</if>",
		"<if test='cardno!=null'>cardno=#{cardno},</if>",
		"<if test='alipay!=null'>alipay=#{alipay},</if>",
		"<if test='wpay!=null'>wpay=#{wpay},</if>",
		"updatetime=sysdate()",
		"where userseq=#{userseq}</script>"})
	void updateUserAuth(UserAuth user)throws Exception;

	@Update({"update user_auth set password=#{password} where userseq=#{userseq}"})
	void changePassword(UserAuth auth)throws Exception;

	@Insert({"insert into user_score(userseq)values(#{userseq})"})
	void addUserScore(UserScore score)throws Exception;

	@Select({"select * from user where userseq=#{value}"})
	User getUserBySeq(Long userseq)throws Exception;

	@Select({"select * from user where nickname=#{value}"})
	User getUserByNickname(String nickname)throws Exception;

	@Select({"select * from user where loginname=#{value}"})
	User getUserByLoginname(String loginname)throws Exception;

	@Select({"select * from user where mobileno=#{value}"})
	User getUserByMobileno(String mobileno)throws Exception;

	@Select({"select * from user where openid=#{value}"})
	User getUserByOpenid(String openid)throws Exception;

	@Select({"select * from user_review where userseq=#{userseq} ",
		"and orderseq<#{minvalue} order by orderseq limit 0, ${pageSize}"})
	List<UserReview> queryUserReviewList(ScrollQueryUserReview params)throws Exception;
	

	@Insert({"insert into user_review(",
		"orderseq,",
		"goodsseq,",
		"sellerseq,",
		"userseq,",
		"score,",
		"review,",
		"imgs,",
		"createtime)values(",
		"#{orderseq},",
		"#{goodsseq},",
		"#{sellerseq},",
		"#{userseq},",
		"#{score},",
		"#{review},",
		"#{imgs},",
		"sysdate())"})
	void addUserReview(UserReview ur)throws Exception;

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
