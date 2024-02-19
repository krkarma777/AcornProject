package com.controller.reviews;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.ReportDTO;
import com.service.ReviewService;


@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에서 로그인 정보 파싱
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			String userId = login.getUserId();
			String postId = request.getParameter("postId");
			String reason = request.getParameter("reason");
			
			// dto에 담아서 전달
			ReportDTO report = new ReportDTO();
			report.setPostId(postId);
			report.setReason(reason);
			report.setUserId(userId);
			
			//System.out.println("updatelike: " + map);
			
			ReviewService service = new ReviewService();
			service.reportReview(report);
			request.setAttribute("mesg", "해당 리뷰를 신고하였습니다.");
		}
		
		// forward
		RequestDispatcher dis = request.getRequestDispatcher("ShowReviewServlet");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
