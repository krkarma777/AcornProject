package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.PostService;
import com.service.ReviewService;

/**
 * Servlet implementation class PostLikeUpdateSevlet
 */
@WebServlet("/PostLikeInsertSevlet")
public class PostLikeInsertSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostLikeInsertSevlet() {
        super();
    }
    //게시물에서 좋아요 버튼 클릭 시 db에 좋아요수 insert 서블릿
    //db에 userId와 postId 없을 경우 (userId, postId, isLike) insert 진행
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		System.out.println("게시글 좋아요수 insert 호출됨");
		
		// 세션에서 로그인 정보 파싱
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			String userId = request.getParameter("userId");
			String postId = request.getParameter("postId");			
			System.out.println("userId : "+userId+", postId : "+postId);
			
			
			//HashMap에 답아서 전달
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("postId", postId);
			System.out.println("map : "+ map);
			
			PostService service = new PostService();
			int n = service.postLike(map);
			System.out.println("insert 개수 : "+n);
		}
		
	}//end doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
