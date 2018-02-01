package shoppingmall.exception;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import shoppingmall.pub.Result;


public class GolbalExceptionHandler implements HandlerExceptionResolver {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private MessageSource messageSource;
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private String printError(Throwable e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		String str = sw.toString();
		log.error(str);
		return str;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse resp, Object handler, Exception exp) {
		String errorCode, errorMessage;
		printError(exp);
		String error = buildStacks(exp);
		if(exp instanceof BaseException){
			errorCode = exp.getMessage();
			String err = ((BaseException)exp).getLocalizedMessage(messageSource);
			errorMessage = err != null ? err : exp.getMessage();
		}else{
			errorCode = "system.error";
			errorMessage = "系统错误";
		}
		writeError(error);
		Result result = new Result(errorCode, errorMessage, error);
		request.setAttribute("result", result);
		return new ModelAndView("error");
	}
	
	public String getFilePrefix() {
		String accessFile = "error";
		String path = System.getProperty("chain.access.log.path");
		if(path != null){
			return path + File.separator + accessFile;
		}
		return accessFile;
	}

	private void writeError(String error) {
		log.error(error);
	}

	private String buildStacks(Exception exp) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		int pos;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		pw.append(df.format(new Date())).append(" ");
		pw.append(exp.getClass().getName()).append(":").append(exp.getMessage());
		if(exp instanceof BaseException){
			String err = ((BaseException)exp).getLocalizedMessage(messageSource);
			pw.append(":").append(err);
		}
		pw.println();
		for(StackTraceElement stack : exp.getStackTrace()){
			String className = stack.getClassName();
			if(!className.startsWith("shoppingmall.")){
				continue;
			}
			pos = className.lastIndexOf('.');
			if(pos > 0){
				className = className.substring(pos + 1);
			}
			String method = stack.getMethodName();
			int line = stack.getLineNumber();
			pw.append(className).append(":").append(method).append("(line:").print(line);
			pw.append(")").println();
		}
		pw.flush();
		return sw.toString();
	}
}
