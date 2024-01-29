package com.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MyPageService;


/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/CommentDelServlet")
public class CommentDelServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
		 String nextPage = null;
	      if(dto!=null) {
	       String comId = request.getParameter("comId");
	       System.out.println(comId);
	       MyPageService service = new MyPageService();
	       int n = service.commDel(Integer.parseInt(comId));
	       System.out.println(n);
			nextPage = "MyCommServlet";

	      }else {
			  nextPage = "Login";
			  session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		  }
		
		 response.sendRedirect(nextPage);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
