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

//아이디 찾기 창에서 정보 출력을 위한 서블릿
@WebServlet("/SearchIDServlet")
public class SearchIDServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));

		MemberService serv = new MemberService();
		MemberDTO dto = serv.findUserId(userName, ssn1, ssn2);

		//입력한 이름과 주민번호가 일치하면 찾은 아이디 정보 출력
	    if (dto != null) {
	    	request.setAttribute("found_userId", dto);
	    	RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Find_Info/viewID.jsp");
	    	dis.forward(request, response);

	    //입력한 이름과 주민번호가 불일치하면 찾은 아이디 정보 출력
	    } else {
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Find_Info/cantFindUserdata.jsp");
			dis.forward(request, response);
	    }
	}
}
