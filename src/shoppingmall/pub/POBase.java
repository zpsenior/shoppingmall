package shoppingmall.pub;

import java.util.Date;
import java.util.List;

public abstract class POBase {
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	private Environment env;
	
	protected Environment getEnvironment(){
		return env;
	}
	
	public void bindEnvironment(Environment env){
		this.env = env;
	}
	
	protected void bindEnvironment(List<? extends POBase> list){
		if(list != null){
			for(POBase po : list){
				po.bindEnvironment(env);
			}
		}
	}
}