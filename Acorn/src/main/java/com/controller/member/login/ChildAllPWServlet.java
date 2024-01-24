package com.controller.member.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//비밀번호 전체 출력 자식창 MVC용(미구현)
@WebServlet("/FindAllPW")
public class ChildAllPWServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);

		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/member/Find_Info/childFindAllPW.jsp");
		dis.forward(request, response);
	
	}
}
