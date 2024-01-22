package com.controller.board.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.PostService;

@WebServlet("/board/movie/delete")
public class MovieBoardDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// 유저 로그인 세션 검증 로직 필요
    	
    	// postId 파라미터 파싱
        String postIdParam = request.getParameter("postId");

        if (postIdParam != null && !postIdParam.isEmpty()) {
            try {
                Long postId = Long.parseLong(postIdParam);

                // postId를 사용하여 리소스 삭제 수행
                PostService service = new PostService();
                service.delete(postId);

                response.sendRedirect("/Acorn/board/movie");

            } catch (NumberFormatException e) {
                // postId가 올바른 형식이 아닌 경우에 대한 처리
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // postId가 제공되지 않은 경우에 대한 처리
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

    }
}
