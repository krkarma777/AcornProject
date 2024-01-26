package com.controller.reviews;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.ReviewService;

@WebServlet("/UpdateLikeServlet")
public class UpdateLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateLikeServlet() {
        super();
    }

    // 비동기 공감 버튼 토글(on/off) DB 업데이트 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션에서 로그인 정보 파싱
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			String userId = request.getParameter("userId");
			String postId = request.getParameter("postId");
			String isLike = request.getParameter("isLike");//
			
			// HashMap에 담아서 전달
			HashMap< String, String > map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("postId", postId);
			map.put("isLike", isLike);
			//System.out.println("updatelike: " + map);
			
			ReviewService service = new ReviewService();
			service.UpdateLike(map);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
