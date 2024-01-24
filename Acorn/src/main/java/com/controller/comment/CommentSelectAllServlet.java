package com.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.CommentDTO;
import com.service.CommentService;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/CommentSelectAllServlet")
public class CommentSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<CommentDTO> list = new ArrayList<>();
		Long postId = Long.parseLong(request.getParameter("postId"));
		CommentService service = new CommentService();
		list = service.selectAllByPostId(postId);
		System.out.println(list);
		
		HashMap<String,List<CommentDTO>> map = new HashMap<>();
		map.put("commentDBList", list);
		
		
		//아래서부터 중요, map으로 저장된 데이터를 json형태로 바꾸기 위해 lib폴더에 jackson으로 시작하는 jar파일 3개가 추가되었고
		//아래 코드로 jar 활성화 시켜서 map -> json으로 했음. ajax로 map을 보내려면 이 방법뿐이었음
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		  
			
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
