package com.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.board.PostSaveDTO;
import com.service.PostService;

@WebServlet("/board/save")
public class BoardWriteSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ajax로 전달된 데이터 파싱
		String userId = request.getParameter("userId");
		String postSaveTitle = request.getParameter("postTitle");
		String postSaveText = request.getParameter("postText");
		
		PostSaveDTO dto = new PostSaveDTO(null, userId, postSaveTitle, postSaveText, null);
		
		PostService service = new PostService();
		service.insertPostSave(dto);
		
		System.out.println(dto);
	}

}//end class
