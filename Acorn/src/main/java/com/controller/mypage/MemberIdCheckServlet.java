package com.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MyPageService;

/**
 * Servlet implementation class MemberUIServlet
 */
@WebServlet("/MemberIdCheckServlet")
public class MemberIdCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String userId = request.getParameter("userId");
		
		MyPageService service = new MyPageService();
		int count = service.idCheck(userId);
		String mesg = "아이디 사용 가능";
		if(count==1) {
		  mesg = "아이디 중복";	
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(mesg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
