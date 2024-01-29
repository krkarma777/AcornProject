package com.controller.reviews;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.ReviewDTO;
import com.service.ReviewService;

@WebServlet("/WriteReviewServlet")
public class WriteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteReviewServlet() {
        super();
    }

    // 비동기 리뷰 작성 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션에서 로그인 정보 파싱
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		//로그인 정보가 존재하지 않을 때
		if(login==null) {
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
			
		// 로그인 정보가 존재할 때
		} else {
			// 인코딩
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// 데이터 파싱
			Long contId = Long.parseLong(request.getParameter("contId"));
			String userId = request.getParameter("userId");
			String postText = request.getParameter("postText");
			String nickname = request.getParameter("nickname");
			ReviewDTO review = new ReviewDTO(null, "review", userId, contId, null, null, null, postText, nickname, null, null, null, null);
			
			// DB에 등록, 등록된 review객체 받아옴
			ReviewService service = new ReviewService();
			review = service.writeReview(review);
			
			// javascript에서 텍스트를 JSON.parse()로 json객체로 변환하여 사용할 수 있게하기 위해 하는 작업
			String jsonText = "{"
					+ "\"postId\": \""+review.getPostId()+"\","
					+ "\"postText\": \""+review.getPostBoard()+"\","
					+ "\"userId\": \""+review.getUserId()+"\","
					+ "\"contId\": \""+review.getContId()+"\","
					+ "\"postTitle\": \""+review.getPostTitle()+"\","
					+ "\"postDate\": \""+review.getPostDate()+"\","
					+ "\"editDate\": \""+review.getEditDate()+"\","
					+ "\"postText\": \""+review.getPostText()+"\","
					+ "\"nickname\": \""+review.getNickname()+"\""
					+ "}";
			//응답
			out.print(jsonText);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
