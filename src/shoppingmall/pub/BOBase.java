package shoppingmall.pub;

import java.util.List;

import shoppingmall.dao.DAO;

public class BOBase {

	private Environment env;
	
	private DAO dao;
	
	public BOBase(Environment env){
		this.env = env;
		this.dao = new DAO(env.getSession());
	}
	
	protected Environment getEnvironment(){
		return env;
	}
	
	protected void bindEnvironment(List<? extends POBase> list){
		if(list != null){
			for(POBase po : list){
				bindEnvironment(po);
			}
		}
	}
	
	protected void bindEnvironment(POBase po){
		if(po != null){
			po.bindEnvironment(env);
		}
	}

	public DAO getDAO() {
		return dao;
	}
}
