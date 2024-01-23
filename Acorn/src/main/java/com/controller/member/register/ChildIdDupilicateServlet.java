package com.controller.member.register;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//아이디 중복 자식창 MVC용(미구현)
@WebServlet("/ChildIdDupilicateServlet")
public class ChildIdDupilicateServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/member/Register/childCheckIDDupilicate.jsp");
		dis.forward(request, response);
		
	}

}
