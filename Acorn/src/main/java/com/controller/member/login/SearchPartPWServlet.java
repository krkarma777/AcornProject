package com.controller.member.login;

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

//비밀번호 찾기(일부) 페이지 --> 출력
@WebServlet("/SearchPartPWServlet")
public class SearchPartPWServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));
		
		MemberService serv = new MemberService();
		MemberDTO dto = serv.findUserPW(userId, userName, ssn1, ssn2);

		HttpSession session = request.getSession();

		//입력한 아이디 / 이름 / SSN이 일치하면 비밀번호(일부) 출력
		if (dto != null) {
			request.setAttribute("foundUserPW", dto);
	    	RequestDispatcher dis = request.getRequestDispatcher("Find_Info/viewPartPW.jsp");
	    	dis.forward(request, response);

		//불일치하면 유저 미확인 창으로 연경
		} else {
	        response.sendRedirect("Find_Info/cantFindUserdata.jsp");
	    }
	}
}
