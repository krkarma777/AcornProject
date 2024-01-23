package com.controller.reviews;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.RateDTO;
import com.service.ReviewService;

@WebServlet("/UpdateScoreServlet")
public class UpdateScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateScoreServlet() {
        super();
    }

    // 비동기 별점 업데이트 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션에서 로그인 정보 파싱
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			
			//데이터 파싱
			String userId = login.getUserId();
			long contId = Long.parseLong(request.getParameter("contId"));
			int score = Integer.parseInt(request.getParameter("score"));
			
			// dto 생성
			RateDTO dto = new RateDTO(userId, contId, score);
			
			//DB에 별점 업데이트 작업
			ReviewService service = new ReviewService();
			service.UpdateScore(dto);
			
			//반환값 따로 없음
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
