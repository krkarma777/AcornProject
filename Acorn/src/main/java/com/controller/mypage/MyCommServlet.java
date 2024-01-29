package com.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CommentDTO;
import com.dto.MemberDTO;
import com.service.MyPageService;

/**
 * Servlet implementation class MyCommSelectServlet
 */
@WebServlet("/MyCommServlet")
public class MyCommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
		System.out.println(dto); 
		String nextPage = null;
	      if(dto!=null) {
	    	  String userId =dto.getUserId();
	    	  MyPageService service = new MyPageService();
	            
	    	  List<CommentDTO> list = service.selectMyComm(userId);

	            // 단일 사용자의 CommentDTO List를 JSP 페이지로 전달
	            request.setAttribute("commentList", list);
	            System.out.println("list>>>>>"+list);
	            // 사용자의 userid를 JSP 페이지로 전달
	            request.setAttribute("userId", userId);
	            System.out.println("userId>>>>>"+userId);
	            // JSP 페이지로 이동
	            nextPage = "mypage/MyPageComment.jsp";
	       
			
	      }else {
			  nextPage = "Login";
			  session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		  }
	      RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			dis.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
