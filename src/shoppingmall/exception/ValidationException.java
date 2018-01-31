package shoppingmall.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -5069049967391790865L;

	public ValidationException(String msg, Object... params){
		super(msg);
	}
}
