package shoppingmall.pub;

import java.util.Date;

import shoppingmall.controller.Environment;
import shoppingmall.dao.DAO;

public abstract class POBase {
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	private DAO dao;
	private Environment env;
	
	protected Environment getEnvironment(){
		if(env == null){
			env = Environment.getInstance();
		}
		return env;
	}

	protected DAO getDAO() {
		if(dao == null){
			dao = new DAO(getEnvironment().getSession());
		}
		return dao;
	}
}
