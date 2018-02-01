package shoppingmall.wauth;

import java.util.Map;

public abstract class WAuthResponse {

	public void parse(Map<String, String> bean) {
		setFields(bean);
	}

	protected abstract void setFields(Map<String, String> bean);

}
