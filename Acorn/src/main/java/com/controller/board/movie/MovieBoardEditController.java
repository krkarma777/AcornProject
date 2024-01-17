package com.controller.board.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.PostDTO;
import com.service.PostService;

@WebServlet("/board/movie/edit")
public class MovieBoardEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	// 수정할 글의 ID를 받아옵니다.
        request.setCharacterEncoding("utf-8");
        String postIdParam = request.getParameter("postId");

        if (postIdParam != null && !postIdParam.isEmpty()) {
            try {
                Long postId = Long.parseLong(postIdParam);

                // postId를 사용하여 해당 글의 정보를 불러옵니다.
                PostService service = new PostService();
                PostDTO post = service.select(postId);

                // 수정 페이지로 이동하면서 해당 글의 정보를 전달합니다.
                request.setAttribute("post", post);
                request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // postId가 올바른 형식이 아닌 경우에 대한 처리
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // 필수 파라미터가 제공되지 않은 경우에 대한 처리
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	request.setCharacterEncoding("utf-8");
        // 수정된 글의 내용을 받아옵니다.
        String postIdParam = request.getParameter("postId");
        String updatedTitle = request.getParameter("title");
        String updatedContent = request.getParameter("content");

        if (postIdParam != null && !postIdParam.isEmpty() && updatedTitle != null && updatedContent != null) {
            try {
                Long postId = Long.parseLong(postIdParam);

                // postId를 사용하여 해당 글을 업데이트합니다.
                PostService service = new PostService();
                service.update(postId, updatedTitle, updatedContent);

                // 수정이 완료되면 해당 글로 이동하거나 다른 처리를 할 수 있습니다.
                response.sendRedirect(request.getContextPath() + "/board/movie/content?postId=" + postId);
            } catch (NumberFormatException e) {
                // postId가 올바른 형식이 아닌 경우에 대한 처리
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // 필수 파라미터가 제공되지 않은 경우에 대한 처리
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
