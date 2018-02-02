package shoppingmall.cache;

import shoppingmall.po.Goods;
import shoppingmall.po.User;
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
	
	private final static String PREFIX_USER_KEY = "user.";
	private final static String PREFIX_GOODS_KEY = "goods.";
	
	public User getUser(long userseq){
		return (User)get(PREFIX_USER_KEY + userseq);
	}
	public void addUser(User user){
		set(PREFIX_USER_KEY + user.getUserseq(), 0, user);
	}
	public void removeUser(long userseq){
		delete(PREFIX_USER_KEY + userseq);
	}
	
	public Goods getGoods(long goodsseq){
		return (Goods)get(PREFIX_GOODS_KEY + goodsseq);
	}
	public void addGoods(Goods goods){
		set(PREFIX_GOODS_KEY + goods.getGoodsseq(), 0, goods);
	}
	public void removeGoods(long goodsseq){
		delete(PREFIX_GOODS_KEY + goodsseq);
	}
}
