package com.web.controller.board.movie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.dto.PostDTO;
import com.web.service.PostService;


@WebServlet("/board/movie/content") // /board/movie/content?postId=10
public class MovieBoardContentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	// postId 파싱
	    	Long postId = Long.parseLong(request.getParameter("postId"));
	    	
	    	// postId로 select 
	    	PostService service = new PostService();
	    	PostDTO post = service.select(postId);
	    	
	    	// 제목, 내용(html) getter + 추가 가능
	    	String postText = post.getPostText();
	    	String title = post.getPostTitle();
	    	
	    	// request 객체에 바인딩
	    	request.setAttribute("postText", postText);
	    	request.setAttribute("title", title);
	    	
	    	// jsp에 forward
	        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/content.jsp");
	        dis.forward(request, response);
	        
	    }

	   
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 
		}
	    

}


