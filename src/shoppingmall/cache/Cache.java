package shoppingmall.cache;

import net.spy.memcached.MemcachedClient;

public class Cache {

	private MemcachedClient mc = null;

	protected void set(String key, int exp, Object value) {
		mc.set(key, exp, value);
	}

	public void add(String key, int exp, Object value) {
		mc.add(key, exp, value);
	}
	
	protected Object get(String key) {
		return mc.get(key);
	}
	
	protected void delete(String key) {
		mc.delete(key);
	}
	
	protected long inc(String key) {
		return mc.incr(key, 1);
	}
	
	protected long dec(String key) {
		return mc.decr(key, 1);
	}

	public String getWXSession(String sessionid){
		return (String)get(sessionid);
	}
	
	public void setWXSession(String sessionid, String value){
		set(sessionid, 3600 * 24, value);
	}
}
