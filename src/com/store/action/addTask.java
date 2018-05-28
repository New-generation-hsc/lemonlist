package com.store.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.loginDao;
import com.store.service.loginService;

public class addTask extends HttpServlet {

    private loginService service;
	/**
	 * Constructor of the object.
	 */
	public addTask() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String path = request.getContextPath();  
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html; charset=utf-8"); 
        PrintWriter out = response.getWriter();
        
		String taskName = request.getParameter("taskname");
		if(taskName == ""){
			out.write("<script language='javascript'>alert('鐢ㄦ埛鍚嶄笉鑳戒负绌�);window.location.href='index.jsp'</script>");
		}
		String usernameString = new String(taskName.getBytes("ISO-8859-1"),"UTF-8");	
		String date = request.getParameter("date");
		if(date == ""){
			out.write("<script language='javascript'>alert('瀵嗙爜涓嶈兘涓虹┖');window.location.href='index.jsp'</script>");
		}
		System.out.println("taskName:"+usernameString+"date:"+date);
		List <Object> param = new ArrayList<Object>();
		param.add(usernameString);
		param.add(date);
		boolean flag = false;
		try {
			flag = service.addTask(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			System.out.println("鍒涘缓鎴愬姛!");
			out.write("<script language='javascript'>alert('鍒涘缓鎴愬姛');window.location.href='index.jsp'</script>");
		}
		else{
			System.out.println("鍒涘缓澶辫触");
			out.write("<script language='javascript'>alert('鍒涘缓澶辫触');window.location.href='index.jsp'</script>");
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new loginDao();
	}

}
