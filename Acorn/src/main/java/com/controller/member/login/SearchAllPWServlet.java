package com.controller.member.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

//전체 비밀번호 찾기 자식창 --> 전체 비밀번호 출력창
@WebServlet("/SearchAllPW")
public class SearchAllPWServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");

		MemberService serv = new MemberService();
		MemberDTO dto = serv.selectMemberData(userId);
		
		request.setAttribute("foundUserInfo", dto);
																								//디버그 코드************************************************************************
																								RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Find_Info/viewAllPW.jsp");
																								//디버그 코드************************************************************************
//		RequestDispatcher dis = request.getRequestDispatcher("SendEmailServlet");
		dis.forward(request, response);
	}
}
