package shoppingmall.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shoppingmall.admin.bo.AdminMutation;
import shoppingmall.admin.bo.AdminQuery;
import shoppingmall.pub.Environment;

@Controller
@RequestMapping("/admin")
public class AdminController extends MainController {
	
	public AdminController()throws Exception{
		init("shoppingmall/config/admin.sdl", new String[]{AdminQuery.class.getName(), AdminMutation.class.getName()});
	}
	
	@RequestMapping(value="Query")
	public void doQuery(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Environment env = (Environment)request.getAttribute(GRAPHQL_ENVIRONMENT);
		PrintWriter out = response.getWriter();
		execute(request, new AdminQuery(env), "Query", out);
	}
	
	@RequestMapping(value="Mutation")
	public void doMutation(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Environment env = (Environment)request.getAttribute(GRAPHQL_ENVIRONMENT);
		PrintWriter out = response.getWriter();
		execute(request, new AdminMutation(env), "Mutation", out);
	}

}
