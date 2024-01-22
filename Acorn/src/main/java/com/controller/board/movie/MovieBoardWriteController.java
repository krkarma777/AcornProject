package com.controller.board.movie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.PostDTO;
import com.service.PostService;

@WebServlet("/board/movie/write")
public class MovieBoardWriteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 글 작성 페이지로 이동
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 글 작성 페이지(post.jsp)로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/post.jsp");
        dispatcher.forward(request, response);
    }

    // 글 작성 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼에서 전송된 데이터를 파싱
        request.setCharacterEncoding("utf-8");
        String userId = request.getParameter("userid");
        String postTitle = request.getParameter("title");
        String postText = request.getParameter("content");
        String postBoard = "movie"; // 게시판 구분

        // PostDTO 객체 생성 및 데이터 설정
        PostDTO post = new PostDTO(userId, postTitle, postText, postBoard);
        
        // PostService를 통해 글 작성 요청 처리
        PostService service = new PostService();
        Long postId = service.insertContent(post); // insertContent 메서드에서 자동 생성된 postId를 반환

        // 작성된 글로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/board/movie/content?postId=" + postId);
    }
}
