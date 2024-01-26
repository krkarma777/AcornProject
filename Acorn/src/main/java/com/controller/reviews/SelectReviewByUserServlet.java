package com.controller.reviews;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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

@WebServlet("/SelectReviewByUserServlet")
public class SelectReviewByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectReviewByUserServlet() {
        super();
    }

    // 비동기 자신의 리뷰, 별점 불러오기 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		String contId = request.getParameter("contId");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("contId", contId);
		//System.out.println(request.getParameter("userId"));
		ReviewService service = new ReviewService();
		ReviewDTO review = service.SelectReviewByUser(map);
		//PostDTO review = (PostDTO) result.get("review");
		if(review!=null) {
			int score=0;
			if(review.getScore()!=null) {
				score = Integer.parseInt(review.getScore());
			}
			//System.out.println("점수" + score);
				
			
			//System.out.println(request.getParameter("userId"));
			String jsonText = "{"
					+ "\"postId\": \""+review.getPostId()+"\","
					+ "\"postText\": \""+review.getPostBoard()+"\","
					+ "\"userId\": \""+review.getUserId()+"\","
					+ "\"contId\": \""+review.getContId()+"\","
					+ "\"postTitle\": \""+review.getPostTitle()+"\","
					+ "\"postDate\": \""+review.getPostDate()+"\","
					+ "\"editDate\": \""+review.getEditDate()+"\","
					+ "\"postText\": \""+review.getPostText()+"\","
					+ "\"nickname\": \""+review.getNickname()+"\","
					+ "\"score\": \""+score+"\""
					+ "}";
			out.print(jsonText);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
