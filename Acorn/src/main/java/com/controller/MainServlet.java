package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.board.PostPageDTO;
import com.service.PostService;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("cg");
		String nextPage = "WEB-INF/main.jsp";
		
		PostService service = new PostService();
		List<PostPageDTO> movieList = service.selectAll(new HashMap<String, String>() {{
		    put("board", "movie");
		    put("postCount", "5");
		}});
		List<PostPageDTO> movieMeetList = service.selectAll(new HashMap<String, String>() {{
		    put("board", "movieMeet");
		    put("postCount", "5");
		}});
		List<PostPageDTO> movieInfoList = service.selectAll(new HashMap<String, String>() {{
		    put("board", "movieInfo");
		    put("postCount", "5");
		}});
		
		
		
		request.setAttribute("movieList", movieList);
		request.setAttribute("movieMeetList", movieMeetList);
		request.setAttribute("movieInfoList", movieInfoList);
		
		if(category != null) {
		    nextPage = categoryDispatcher(category);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);//main.jsp로 이동
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String categoryDispatcher(String category) {
	    switch(category) {
	        case "movie":
	            return "WEB-INF/movieHome.jsp";
	        case "book":
	            return "WEB-INF/BookHome.jsp";
	        case "tv":
	            return "WEB-INF/TvHome.jsp";
	        default:
	            return "WEB-INF/main.jsp"; 
	    }
	}


}
