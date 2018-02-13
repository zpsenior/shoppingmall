package shoppingmall.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shoppingmall.admin.bo.AdminMutation;
import shoppingmall.admin.bo.AdminQuery;
import shoppingmall.exception.DataValidateException;
import shoppingmall.po.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController extends MainController {
	
	public AdminController()throws Exception{
		init("shoppingmall/config/admin.sdl", new String[]{AdminQuery.class.getName(), AdminMutation.class.getName()});
	}
	
	@RequestMapping(value="Query")
	public void doQuery(HttpServletRequest request, HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		execute(request, new AdminQuery(), "Query", out);
	}
	
	@RequestMapping(value="Mutation")
	public void doMutation(HttpServletRequest request, HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter();
		execute(request, new AdminMutation(), "Mutation", out);
	}

	@Override
	protected Object getUser(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Admin.ADMININFO);
		if(obj != null && obj instanceof Admin){
			return obj;
		}
		throw new DataValidateException("admin.not.login");
	}

}
