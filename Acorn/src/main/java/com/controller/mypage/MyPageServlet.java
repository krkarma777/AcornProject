package com.controller.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MyPageService;

/**
 * Servlet implementation class MemberUIServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	      HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");

	      String nextPage = null;
	      if(dto!=null) {
			  nextPage = "MypageLayout.jsp";
              String userId = dto.getUserId();
              System.out.println(userId);
              MyPageService service = new MyPageService();
              MemberDTO x = service.mypage(userId);
              session.setAttribute("loginUser", x);
              System.out.println("mypage이동");
	      }else {
			  nextPage = "main";
			  request.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		  }
	      RequestDispatcher dis =
	    		  request.getRequestDispatcher(nextPage);
	      dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
