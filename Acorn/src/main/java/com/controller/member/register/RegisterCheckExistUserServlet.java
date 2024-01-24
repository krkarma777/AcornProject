package com.controller.member.register;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//회원가입 2단계 기존 유저 검증 단계 연결
@WebServlet("/CheckExistUser")
public class RegisterCheckExistUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//약관 동의 여부를 DB에 저장할 지 여부는 현재 미정	
		//DB에 저장할 것을 대비해서 
		
		String checked_Agreement = request.getParameter("checked_Agreement");
		String checked_Info = request.getParameter("checked_Info");
		String checked_Withdraw = request.getParameter("checked_Withdraw");

																										//디버그 코드***************************************************
																										System.out.println(
																													"checked_Agreement : " + checked_Agreement+"\n"+ 
																													"checked_Info : " + checked_Info+"\n"+
																													"checked_Withdraw : " + checked_Withdraw);
																										//디버그 코드***************************************************		
		
		HttpSession session = request.getSession();
		//session.setAttribute("checked_Agreement", checked_Agreement);
		//session.setAttribute("checked_Info", checked_Info);
		//session.setAttribute("checked_Withdraw", checked_Withdraw);
		
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerCheckExistUserdata.jsp");
		dis.forward(request, response);
		
	}

}
