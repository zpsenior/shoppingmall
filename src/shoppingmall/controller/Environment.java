package shoppingmall.controller;

import org.apache.ibatis.session.SqlSession;

import shoppingmall.cache.Cache;
import shoppingmall.po.User;

public class Environment {
	
	private static ThreadLocal<Environment> tl = new ThreadLocal<Environment>();

	private SqlSession session;
	private Cache cache;
	private Object user;
	
	Environment(SqlSession session, Cache cache, Object user){
		this.session = session;
		this.cache = cache;
		this.user = user;
	}
	
	protected static void addEnvironment(SqlSession session, Cache cache, Object user){
		Environment env = tl.get();
		if(env == null){
			env = new Environment(session, cache, user);
			tl.set(env);
		}
	}

	public static Environment getInstance() {
		return tl.get();
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
