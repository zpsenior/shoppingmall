package shoppingmall.exception;

public class ThirdSysErrException extends BaseException {

	private static final long serialVersionUID = 5557731064375141316L;
	
	public ThirdSysErrException(String msg, String... params){
			super(msg, params);
	}
}
