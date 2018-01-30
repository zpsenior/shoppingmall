package shoppingmall.pub;

import java.util.List;

public class BOBase {

	private Environment env;
	
	public BOBase(Environment env){
		this.env = env;
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
}
