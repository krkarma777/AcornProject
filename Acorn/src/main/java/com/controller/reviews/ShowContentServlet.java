package com.controller.reviews;

import java.io.IOException;
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
		
		//임시 userId 세션에 저장 (나중에 삭제)
		HttpSession session = request.getSession();
		MemberDTO login = new MemberDTO("1", "1234", "배성준", 111111, 2222222,
				"male", "bsj", "010", "2469", "6235",
				"bsj4387", "naver.com", null, "1");
		session.setAttribute("login", login);
		
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
		List<ReviewDTO> reviewList = service.selectReviews(contId);
		request.setAttribute("reviewList", reviewList);
		
		// forward
		RequestDispatcher dis = request.getRequestDispatcher("Review/contentViewer.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
