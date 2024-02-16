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
		
		// 자신이 누른좋아요 정보 가져오기 위해 본인의 유저아이디 저장
		String likeUserId = null;
		if(login!=null) {
			likeUserId = login.getUserId();
			
		}
		
		// request에서 contId 파싱
		String contId = request.getParameter("contId"); 
		
		//임시 컨텐츠 데이터 생성 (나중에 삭제)
		if(contId==null) {
			contId = "1";
		}
		
		// 오류 처리 (컨텐츠아이디 정보 부재)
		// contId가 없다면 예외처리
		if(contId==null) {
			// 클릭한 이전 페이지로 이동
			response.sendRedirect("MoveToContentsHomeServlet");
			
		} else { //컨텐츠id 있을시
			// DB에서 컨텐츠정보 가져오기
			ContentDTO content = service.selectContent(contId);
			request.setAttribute("content", content);
			
			// DB에서 컨텐츠에 해당하는 리뷰리스트 가져와서 전달
			// 최신순으로 8개 select
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("contId", contId);
			map.put("likeUserId", likeUserId);//페이지 사용중인 유저id (각 리뷰에 좋아요 눌렀는지 불러오기 위하여 전달)
			List<ReviewDTO> reviewList = service.selectReviews(map);
			request.setAttribute("reviewList", reviewList);
			
			// 별점 리스트 가져와서 전달 (평균별점 계산용)
			List<RateDTO> rateList = service.selectRates(contId);
			request.setAttribute("rateList", rateList);
			
			// forward
			RequestDispatcher dis = request.getRequestDispatcher("Review/contentViewer.jsp");
			dis.forward(request, response);
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
