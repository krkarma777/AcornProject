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
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userpw = request.getParameter("userpw");
		
		// 로그인한 회원의 아이디 정보 얻어오기
		// 방법1. form에서 hidden으로 보내기
		// String userId = request.getParameter("userId");
		// 방법2. session에 담겨있는 loginUser(로그인한 회원정보) 객체에 있는 아이디 꺼내오기
		HttpSession session = request.getSession();
		// session에서 값을 꺼내오면 object 타입으로 가져와지므로 Member로 형변환해야한다.
		String userId = ((MemberDTO)session.getAttribute("loginUser")).getUserId();
		
		int result = new MyPageService().deleteMember(userId, userpw);
		
		
		if(result > 0) {
			session.setAttribute("alertMsg", "회원 탈퇴되었습니다.");
			// invalidate() 메소드를 사용하면 session이 초기화되므로 alert 메세지까지 초기화된다.
			// removeAttribute() 메소드를 사용해서 회원정보객체만 삭제한다.
			session.removeAttribute("loginUser");
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("loginUser", result);
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}