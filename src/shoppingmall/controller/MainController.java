package shoppingmall.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import graphql4j.engine.GraphQLExecute;
import graphql4j.operation.GraphQL;
import graphql4j.operation.GraphQLBuilder;
import graphql4j.operation.GraphQL.Operation;
import graphql4j.type.GraphQLSchema;
import graphql4j.type.GraphQLSchemaLoader;

import javax.servlet.http.HttpServletRequest;

import shoppingmall.exception.ValidationException;
import shoppingmall.interceptor.Const;

public abstract class MainController implements Const {

	private GraphQLSchema schema;
	private GraphQLExecute exec;
	private GraphQL gql;
	
	private static String readResource(String resource)throws Exception{
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
	
	protected void init(String operationFile, String[] classNames)throws Exception{
		GraphQLSchemaLoader loader = new GraphQLSchemaLoader();
		schema = loader.load(classNames);
		GraphQLBuilder builder = new GraphQLBuilder(schema);
		gql = builder.build(readResource(operationFile));
		exec = new GraphQLExecute(schema);
	}
	
	protected void execute(HttpServletRequest request, Object rootObj, String name, PrintWriter out)throws Exception{
		String action = request.getParameter("action");
		GraphQL.Operation opt = gql.getOperation(action);
		if(opt == null){
			throw new ValidationException("no.find.opt.name", action);
		}
		opt.clearValues();
		bindValue(opt, request);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exec.execute(opt, rootObj, schema.getType(name), pw);
		out.print(pw.toString());
	}


	private void bindValue(Operation opt, HttpServletRequest request)throws Exception{
		String[] names = opt.getArgumentNames();
		for(String name : names){
			String value = request.getParameter(name);
			opt.bindValue(name, value);
		}
	}
}
