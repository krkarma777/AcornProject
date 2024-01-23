package com.controller.member.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

@WebServlet("/memberOneServlet")
public class memberOneServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		
		MemberService serv = new MemberService(); 
		MemberDTO dto = serv.selectOne(userId);
		
		HttpSession session = request.getSession();
		session.setAttribute("memberData", dto);
		
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Test/test_view_one.jsp");
		dis.forward(request, response);
		
		
		
	}

}
