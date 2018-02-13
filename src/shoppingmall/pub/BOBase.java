package shoppingmall.pub;


import shoppingmall.controller.Environment;
import shoppingmall.dao.DAO;

public class BOBase {
	
	private DAO dao;
	private Environment env;
	
	public BOBase(){
		this.dao = new DAO(getEnvironment().getSession());
	}
	
	protected Environment getEnvironment(){
		if(env == null){
			env = Environment.getInstance();
		}
		return env;
	}
	

	protected DAO getDAO() {
		return dao;
	}
}
