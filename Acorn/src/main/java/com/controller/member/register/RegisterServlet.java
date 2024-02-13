package com.controller.member.register;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.service.MemberService;

//회원가입 1단계 약관 동의 단계 연결
@WebServlet("/RegisterTerms")
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userName = request.getParameter("userName");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");

		
		System.out.println("서블릿 "+userName);
		
		
		MemberService serv = new MemberService();
		MemberDTO dto = serv.findUserId(userName, ssn1, ssn2);

		//이름과 SSN이 모두 일치하는 DB정보가 있을 경우, 기존 유저 있음 jsp로 이동
		if (dto != null) {
			request.setAttribute("foundUser", dto);
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/viewExistUserdata.jsp");
			dis.forward(request, response);
			
		//이름과 SSN이 모두 일치하는 DB정보가 없을 경우, 회원가입 2단계로 이동
		} else {
			request.setAttribute("userName", userName);
			request.setAttribute("ssn1", ssn1);
			request.setAttribute("ssn2", ssn2);
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerAgreeTerms.jsp");
			dis.forward(request, response);
		}
		
		
		
	}
}
