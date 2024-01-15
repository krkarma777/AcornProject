package com.web.controller.board.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.dto.PostDTO;
import com.web.service.PostService;

@WebServlet("/board/movie/")
public class MovieBoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET 요청에 대한 처리 로직
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	// 영화 게시판 데이터베이스에서 글 목록을 불러옵니다.
        // URL에서 게시판(board) 이름을 추출하여 동적으로 처리할 수 있도록 하면 더 좋습니다.
        PostService service = new PostService();
        String contentBoard = "movie"; // 추후 동적 처리 가능
        List<PostDTO> list = service.selectAll(contentBoard);
        request.setAttribute("list", list);

        // JSP 페이지로 포워딩
        RequestDispatcher dis = request.getRequestDispatcher("movieBoard.jsp");
        dis.forward(request, response);
    }

    // POST 요청에 대한 처리 로직
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// get 메서드 호출
        doGet(request, response);
    }
}
