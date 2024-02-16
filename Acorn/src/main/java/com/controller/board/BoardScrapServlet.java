package com.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.board.ScrapDTO;
import com.service.ScrapService;

@WebServlet("/scrap")
public class BoardScrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String postId = request.getParameter("postId");
	    String userId = request.getParameter("userId");

	    HashMap<String, String> map = new HashMap<>();
	    map.put("postId", postId);
	    map.put("userId", userId);
	    ScrapService service = new ScrapService();
	    
	    
	    List<ScrapDTO> dto =service.checkScrap(map);
	 
	    
	    
	    // JSON 응답을 위한 content type 설정
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    PrintWriter out = response.getWriter();
	    
	    try {
	    	if(dto.size() == 0) {
	    		service.insert(map);
	    		 // 스크랩 성공 응답
		        out.print("{\"success\": true, \"message\": \"스크랩 성공!\"}");
	    	}else {
	    		out.print("{\"success\": false, \"message\": \"스크랩 실패.\"}");
	    	}
	    } catch (Exception e) {
	        // 스크랩 실패 응답
	    	out.print("{\"success\": false, \"message\": \"스크랩 실패.\"}");
	    } finally {
	        out.flush();
	    }
	}


}
