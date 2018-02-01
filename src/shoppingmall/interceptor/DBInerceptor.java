package shoppingmall.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shoppingmall.po.User;
import shoppingmall.pub.Environment;

public class DBInerceptor extends HandlerInterceptorAdapter implements Const {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private SqlSessionFactory factory;
	
	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		SqlSession sec = factory.openSession();
		User user = getUser(request);
		Environment env = new Environment(sec, user);
		request.setAttribute(GRAPHQL_ENVIRONMENT, env);
		HandlerMethod m = (HandlerMethod)handler;
		String methodName = m.getMethod().getName();
		if("Mutation".equals(methodName)){
			request.setAttribute(DB_TRANSACTION, false);
			log.debug("db startaction");
		}
		return true;
	}

	private User getUser(HttpServletRequest request)throws Exception{
		// TODO 自动生成的方法存根
		return null;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelandview)throws Exception{
		Environment env = (Environment)request.getAttribute(GRAPHQL_ENVIRONMENT);
		Boolean doCommit = (Boolean)request.getAttribute(DB_TRANSACTION);
		if(doCommit != null && !doCommit){
			env.getSession().commit();
			request.setAttribute(DB_TRANSACTION, true);
		}
	}
	

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)throws Exception{
		Boolean doCommit = (Boolean)request.getAttribute(DB_TRANSACTION);
		Environment env = (Environment)request.getAttribute(GRAPHQL_ENVIRONMENT);
		SqlSession sec = env.getSession();
		if(doCommit != null && !doCommit){
			sec.rollback();
		}
		sec.close();
	}
}
