package shoppingmall.app.bo;

import org.apache.log4j.Logger;

import shoppingmall.dao.DAOUser;
import shoppingmall.exception.DataValidateException;
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
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	public AppMutationUser(Environment env) {
		super(env);
	}
	
	@GraphQLField("bindInWeiXin")
	public boolean bindInWeiXin(@GraphQLArgument("param") ParamUser param)throws Exception{
		if(param.getNationcode() == null){
			param.setNationcode("86");
		}
		/*if("86".equals(nationcode)){
			MessageAuthService msgAuth = new MessageAuthService();
			msgAuth.verifyAuthMessage(mobileno, checkcode);
		}else{
			ForeignMessageServer fmServ = new ForeignMessageServer(request);
			fmServ.verifyAuthMessage(nationcode, mobileno, checkcode);
		}*/
		DAOUser daoUser = getEnvironment().getDAOUser();
		User user = daoUser.getUserByMobileno(param.getMobileno());
		if(user == null){
			return false;
		}
		if(!User.STATUS_OK.equals(user.getStatus())){
			throw new DataValidateException("you.had.be.shield");
		}
		user.setOpenid(param.getOpenid());
		daoUser.addOpenidInUser(user);
		return true;
	}
	
	@GraphQLField("registerInWeiXin")
	public boolean registerInWeiXin(@GraphQLArgument("param") ParamUser param)throws Exception{
		if(param.getNationcode() == null){
			param.setNationcode("86");
		}
		String pwd = param.getPassword();
		String confirmpwd = param.getConfirmpwd();
		if(!pwd.equals(confirmpwd)){
			throw new DataValidateException("diff.confirm.password");
		}
		DAOUser daoUser = getEnvironment().getDAOUser();
		User user = daoUser.getUserByOpenid(param.getOpenid());
		if(user != null){
			throw new DataValidateException("your.wx.account.had.be.bind", user.getMobileno());
		}
		user = (User)param;
		daoUser.addUser(user);
		UserAuth auth = new UserAuth();
		auth.setUserseq(user.getUserseq());
		auth.setPassword(pwd);
		daoUser.addUserAuth(auth);
		return true;
	}
	
	@GraphQLField("register")
	public boolean register(@GraphQLArgument("user") ParamUser user)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		if(user.getNationcode() == null){
			user.setNationcode("86");
		}
		daoUser.addUser(user);
		UserAuth auth = new UserAuth();
		auth.setUserseq(user.getUserseq());
		auth.setPassword(user.getPassword());
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
	public User login(@GraphQLArgument("param") ParamUser param)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		User user = daoUser.getUserByLoginname(param.getLoginname());
		if(user == null){
			throw new DataValidateException("error.login.name");
		}
		if(!user.getStatus().equals(User.STATUS_OK)){
			throw new DataValidateException("you.had.be.shield");
		}
		UserAuth auth = daoUser.getUserAuth(user.getUserseq());
		if(!auth.getPassword().equalsIgnoreCase(param.getPassword())){
			log.debug(auth.getPassword() + ":" + param.getPassword());
			throw new DataValidateException("error.login.password");
		}
		User u = new User();
		u.setUserseq(user.getUserseq());
		u.setDevice(param.getDevice());
		u.setPlatform(param.getPlatform());
		daoUser.updateUser(u);
		return user;
	}
	
	@GraphQLField("logout")
	public boolean logout()throws Exception{
		return true;
	}
	
	@GraphQLField("changePassword")
	public boolean changePassword(@GraphQLArgument("user") ParamUser param)throws Exception{
		User user = getEnvironment().getUser();
		DAOUser daoUser = getEnvironment().getDAOUser();
		String oldpwd = param.getOldpwd();
		UserAuth auth = daoUser.getUserAuth(user.getUserseq()); 
		String pwd = auth.getPassword();
		if(!pwd.equalsIgnoreCase(oldpwd)){
			throw new DataValidateException("error.old.password");
		}
		String password = param.getPassword();
		String confirmpwd = param.getConfirmpwd();
		if(!password.equals(confirmpwd)){
			throw new DataValidateException("diff.confirm.password");
		}
		auth.setPassword(pwd);
		daoUser.changePassword(auth);
		return true;
	}
	
	@GraphQLField("updateUserInfo")
	public boolean updateUserInfo(@GraphQLArgument("user") ParamUser user)throws Exception{
		DAOUser daoUser = getEnvironment().getDAOUser();
		if(user.getNickname() != null){
			if(daoUser.getUserByNickname(user.getNickname())!=null){
				throw new DataValidateException("duplicate.nick.name", user.getNickname());
			}
		}
		if(user.getLoginname() != null){
			if(daoUser.getUserByLoginname(user.getLoginname())!=null){
				throw new DataValidateException("duplicate.nick.name", user.getLoginname());
			}
		}
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
