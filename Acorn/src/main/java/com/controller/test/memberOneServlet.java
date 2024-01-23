package com.controller.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;
import com.service.memberService;

@WebServlet("/memberOneServlet")
public class memberOneServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		
		memberService serv = new memberService(); 
		memberDTO dto = serv.selectOne(userId);
		
		HttpSession session = request.getSession();
		session.setAttribute("memberData", dto);
		
		RequestDispatcher dis = request.getRequestDispatcher("Test/test_view_one.jsp");
		dis.forward(request, response);
		
		
		
	}

}
