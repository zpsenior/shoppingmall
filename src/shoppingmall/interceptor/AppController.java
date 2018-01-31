package shoppingmall.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import graphql4j.engine.GraphQLExecute;
import graphql4j.operation.GraphQL;
import graphql4j.operation.GraphQLBuilder;
import graphql4j.type.GraphQLSchema;
import graphql4j.type.GraphQLSchemaLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shoppingmall.app.bo.AppMutation;
import shoppingmall.app.bo.AppQuery;
import shoppingmall.exception.ValidationException;
import shoppingmall.pub.Environment;

public class AppController extends HandlerInterceptorAdapter {
	
	private SqlSessionFactory factory;
	private String operationFile;
	
	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}
	
	public String getOperationFile() {
		return operationFile;
	}

	private GraphQLSchema schema;
	private GraphQLExecute exec;
	private GraphQL gql;
	
	public static String readResource(String resource)throws Exception{
		InputStream is = AppController.class.getClassLoader().getResourceAsStream(resource);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		while(true){
			int cnt = is.read(buff);
			if(cnt < 0){
				break;
			}
			bos.write(buff, 0, cnt);
		}
		return bos.toString();
	}
	
	public void init()throws Exception{
		GraphQLSchemaLoader loader = new GraphQLSchemaLoader();
		schema = loader.load(new String[]{AppQuery.class.getName()});
		GraphQLBuilder builder = new GraphQLBuilder(schema);
		gql = builder.build(readResource(operationFile));
		exec = new GraphQLExecute(schema);
	}
	
	public Environment getEnvironment(HttpServletRequest request){
		SqlSession sec = factory.openSession();
		return new Environment(sec);
	}
	
	private void execute(Environment env, String optName, PrintWriter pw)throws Exception{
		GraphQL.Operation opt = gql.getOperation(optName);
		if(opt == null){
			throw new ValidationException("no.find.opt.name", optName);
		}
		SqlSession sec = env.getSession();
		Object rootObj;
		try{
			if(opt.getOperationType() == GraphQL.TYPE_QUERY){
				rootObj = new AppQuery(env);
			}else{
				try{
					rootObj = new AppMutation(env);
				}catch(Exception e){
					sec.rollback();
					throw e;
				}
				sec.commit();
			}
		}finally{
			sec.close();
		}
		exec.query(opt, rootObj, pw);
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		String url = request.getRequestURI();
		String optName = getOperationName(url);
		StringWriter sw = new StringWriter();
		Environment env = getEnvironment(request);
		execute(env, optName, new PrintWriter(sw));
		PrintWriter pw = response.getWriter();
		pw.println(sw.toString());
		return true;
	}
	

	private String getOperationName(String url) {
		return null;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelandview)throws Exception{
		
	}
	

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)throws Exception{
		
	}
}
