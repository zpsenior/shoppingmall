package shoppingmall.pub;

import org.apache.ibatis.session.SqlSession;

import shoppingmall.cache.Cache;
import shoppingmall.po.User;

public class Environment {

	private SqlSession session;
	private Cache cache;
	private Object user;
	
	public Environment(SqlSession session, Cache cache, Object user){
		this.session = session;
		this.cache = cache;
		this.user = user;
	}

	public SqlSession getSession() {
		return session;
	}
	


	public User getUser() {
		return (User)user;
	}

	public Cache getCache() {
		return cache;
	}
}
