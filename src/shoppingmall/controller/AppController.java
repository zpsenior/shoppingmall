package shoppingmall.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shoppingmall.app.bo.AppMutation;
import shoppingmall.app.bo.AppQuery;
import shoppingmall.pub.Environment;

@Controller
@RequestMapping("/app")
public class AppController extends MainController  {
	
	public AppController()throws Exception{
		init("shoppingmall/config/app.sdl", new String[]{AppQuery.class.getName(), AppMutation.class.getName()});
	}
	
	@RequestMapping(value="Query")
	public void doQuery(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Environment env = (Environment)request.getAttribute(GRAPHQL_ENVIRONMENT);
		PrintWriter out = response.getWriter();
		execute(request, new AppQuery(env), "Query", out);
	}
	
	@RequestMapping(value="Mutation")
	public void doMutation(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Environment env = (Environment)request.getAttribute(GRAPHQL_ENVIRONMENT);
		PrintWriter out = response.getWriter();
		execute(request, new AppMutation(env), "Mutation", out);
	}

}
