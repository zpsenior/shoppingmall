package shoppingmall.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

public abstract class BaseException extends Exception {
	
	private static final long serialVersionUID = 2536591174300676636L;
	
	private String[] parameters;

	public BaseException(String msg, String... params){
		super(msg);
		parameters = new String[params.length];
		for(int i = 0 ; i < params.length; i++){
			parameters[i] = params[i];
		}
	}
	
	public String getLocalizedMessage(MessageSource messageSource) {
		String msg = this.getMessage();
		msg = messageSource.getMessage(msg, parameters, msg, Locale.getDefault());
		return msg;
	}

	public String getErrorParameters(){
		if(parameters!=null&&parameters.length>0){
			String strNew = "";
			for (int index = 0; index < parameters.length; index++)
			{
			    strNew += parameters[index];
			}
			return strNew;
		}else{
			return null;
		}
	}
}
