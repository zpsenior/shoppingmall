package shoppingmall.app.bo;

import shoppingmall.dao.DAOUser;
import shoppingmall.input.ParamUser;
import shoppingmall.input.ParamUserAuth;
import shoppingmall.po.User;
import shoppingmall.po.UserAuth;
import shoppingmall.po.UserScore;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MUser")
public class AppMutationUser extends BOBase {
	
	public AppMutationUser(Environment env) {
		super(env);
	}
	
	@GraphQLField("register")
	public boolean register(@GraphQLArgument("user") ParamUser user)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		daoUser.addUser(user);
		UserAuth auth = new UserAuth();
		auth.setUserseq(user.getUserseq());
		daoUser.addUserAuth(auth);
		UserScore score = new UserScore();
		score.setUserseq(user.getUserseq());
		daoUser.addUserScore(score);
		return true;
	}
	
	@GraphQLField("sendCheckCode")
	public boolean sendCheckCode(@GraphQLArgument("nationcode") String nationcode, @GraphQLArgument("mobileno") String mobileno, @GraphQLArgument("type") int type)throws Exception{
		return true;
	}
	
	@GraphQLField("login")
	public User login(@GraphQLArgument("loginname") String loginname, @GraphQLArgument("password") String password)throws Exception{
		return null;
	}
	
	@GraphQLField("logout")
	public boolean logout()throws Exception{
		return true;
	}
	
	@GraphQLField("changePassword")
	public boolean changePassword(@GraphQLArgument("user") ParamUser user)throws Exception{
		return true;
	}
	
	@GraphQLField("updateUserInfo")
	public boolean updateUserInfo(@GraphQLArgument("user") ParamUser user)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		daoUser.updateUser(user);
		return true;
	}
	
	@GraphQLField("updateUserAuth")
	public boolean updateUserAuth(@GraphQLArgument("auth") ParamUserAuth auth)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		daoUser.updateUserAuth(auth);
		return true;
	}
	
	@GraphQLField("withdrawCash")
	public boolean withdrawCash(@GraphQLArgument("userseq") Long userseq, @GraphQLArgument("amount") int amount)throws Exception{
		return false;
	}
}
