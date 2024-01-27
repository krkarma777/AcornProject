package com.controller.reviews;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.ContentDTO;
import com.dto.MemberDTO;
import com.dto.RateDTO;
import com.dto.ReviewDTO;
import com.service.ReviewService;

@WebServlet("/ShowContentServlet")
public class ShowContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowContentServlet() {
        super();
    }

    // 컨텐츠 클릭시 자세히보기로 이동해주는 서블릿	
    // 해당 컨텐츠 데이터 // 해당 컨텐츠에 해당하는 리뷰들 // 자신의 리뷰(이건 비동기처리함)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		ReviewService service = new ReviewService();
		
		
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO)session.getAttribute("loginUser");
		session.setAttribute("login", login);
		//임시 로그인 정보 세션에 저장 (나중에 삭제)
		MemberDTO login2 = null;
		if(login==null) {
			login2 = new MemberDTO("1", "1234", "배성준", 111111, 2222222,
					"bsj", "male", "010", "2469", "6235",
					"bsj4387", "naver.com", null, "1");
			session.setAttribute("login", login2);
		}
		
		
		// request에서 contId 파싱
		String contId = request.getParameter("contId"); 
		//임시 컨텐츠 데이터 생성 (나중에 삭제)
		if(contId==null) {
			contId = "1";
		}

		// DB에서 컨텐츠정보 가져오기
		ContentDTO content = service.selectContent(contId);
		request.setAttribute("content", content);
		
		// DB에서 컨텐츠에 해당하는 리뷰리스트 가져오기
		// 최신순으로 8개 select
		String likeUserId = null;
		if(login!=null) {
			likeUserId = login.getUserId();
			
		} else if(login2!=null){ //임시데이터 나중에 삭제
			likeUserId = login2.getUserId();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contId", contId);
		map.put("likeUserId", likeUserId);
		List<ReviewDTO> reviewList = service.selectReviews(map);
		request.setAttribute("reviewList", reviewList);
		
		// 별점 리스트 전달 (평균별점 계산용)
		List<RateDTO> rateList = service.selectRates(contId);
		request.setAttribute("rateList", rateList);
		
		// forward
		RequestDispatcher dis = request.getRequestDispatcher("Review/contentViewer.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
