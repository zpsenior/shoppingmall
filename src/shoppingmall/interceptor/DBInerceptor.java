package shoppingmall.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shoppingmall.cache.Cache;

public class DBInerceptor extends HandlerInterceptorAdapter implements Const {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private SqlSessionFactory factory;
	private Cache cache;
	
	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}
	
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		SqlSession sec = factory.openSession();
		request.setAttribute(DB_SESSION, sec);
		request.setAttribute(DB_CACHE, cache);
		HandlerMethod m = (HandlerMethod)handler;
		String methodName = m.getMethod().getName();
		if("Mutation".equals(methodName)){
			request.setAttribute(DB_TRANSACTION, false);
			log.debug("db startaction");
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelandview)throws Exception{
		SqlSession sec = (SqlSession)request.getAttribute(DB_SESSION);
		Boolean doCommit = (Boolean)request.getAttribute(DB_TRANSACTION);
		if(doCommit != null && !doCommit){
			sec.commit();
			request.setAttribute(DB_TRANSACTION, true);
		}
	}
	

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)throws Exception{
		SqlSession sec = (SqlSession)request.getAttribute(DB_SESSION);
		Boolean doCommit = (Boolean)request.getAttribute(DB_TRANSACTION);
		if(doCommit != null && !doCommit){
			sec.rollback();
		}
		sec.close();
	}
}
