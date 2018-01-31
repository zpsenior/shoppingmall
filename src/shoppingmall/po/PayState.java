package shoppingmall.po;

public class PayState{
	private String state;
	private String desc;
	
	public PayState(String state, String desc){
		this.state = state;
		this.desc = desc;
	}
	
	public String getState() {
		return state;
	}
	public String getDesc() {
		return desc;
	}
}
