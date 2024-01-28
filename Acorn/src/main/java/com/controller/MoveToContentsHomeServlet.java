package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.board.PostPageDTO;
import com.service.PostService;

@WebServlet("/MoveToContentsHomeServlet")
public class MoveToContentsHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostService service = new PostService();
		List<PostPageDTO> movieList = service.selectAll("movie");
		List<PostPageDTO> movieMeetList = service.selectAll("movieMeet");
		List<PostPageDTO> movieInfoList = service.selectAll("movieInfo");
		
		request.setAttribute("movieList", movieList);
		request.setAttribute("movieMeetList", movieMeetList);
		request.setAttribute("movieInfoList", movieInfoList);
		
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/ContentsHome.jsp");//ContentsHome.jsp로 이동
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
