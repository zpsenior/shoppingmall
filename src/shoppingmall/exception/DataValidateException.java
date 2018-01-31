package shoppingmall.exception;

public class DataValidateException extends BaseException {

	private static final long serialVersionUID = 5557731064375141316L;
	
	public DataValidateException(String msg, String... params){
			super(msg, params);
	}
}
