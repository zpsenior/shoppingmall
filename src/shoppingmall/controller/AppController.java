package shoppingmall.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shoppingmall.app.bo.AppMutation;
import shoppingmall.app.bo.AppQuery;
import shoppingmall.cache.Cache;
import shoppingmall.dao.DAOUser;
import shoppingmall.exception.DataValidateException;
import shoppingmall.po.User;
import shoppingmall.pub.Environment;

@Controller
@RequestMapping("/app")
public class AppController extends MainController  {
	
	public AppController()throws Exception{
		init("shoppingmall/config/app.sdl", new String[]{AppQuery.class.getName(), AppMutation.class.getName()});
	}
	
	@RequestMapping(value="Query")
	public void doQuery(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Environment env = getEnvironment(request);
		PrintWriter out = response.getWriter();
		execute(request, new AppQuery(env), "Query", out);
	}
	
	@RequestMapping(value="Mutation")
	public void doMutation(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Environment env = getEnvironment(request);
		PrintWriter out = response.getWriter();
		execute(request, new AppMutation(env), "Mutation", out);
	}

	@Override
	protected User getUser(HttpServletRequest request) throws Exception {
		Cache cache = (Cache)request.getAttribute(DB_CACHE);
		Long userseq = Long.parseLong(request.getParameter("userseq"));
		User u = (User)cache.getUser(userseq);
		if(u == null){
			SqlSession sec = (SqlSession)request.getAttribute(DB_SESSION);
			DAOUser daoUser = sec.getMapper(DAOUser.class);
			u = daoUser.getUserBySeq(userseq);
			if(u == null){
				throw new DataValidateException("admin.not.login");
			}
			cache.addUser(u);
		}
		if(!User.STATUS_OK.equals(u.getStatus())){
			throw new DataValidateException("you.had.be.shield");
		}
		return u;
	}

}
