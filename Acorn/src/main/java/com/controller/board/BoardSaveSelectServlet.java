package com.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.board.PostSaveDTO;
import com.service.PostService;


/**
 * Servlet implementation class BoardSaveSelectServlet
 */
@WebServlet("/BoardSaveSelectServlet")
public class BoardSaveSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSaveSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = (String)request.getAttribute("userId");		
		PostService service = new PostService();
		
		List<PostSaveDTO> list = service.postSaveSelect(userId);
		System.out.println(list);
		
		request.setAttribute("postSaveList", list);
		request.getRequestDispatcher("/Acorn/board/write").forward(request, response);
	    
//	    JSONArra jsonArray = new JSONArray();
//	    for (PostSaveDTO post : list) {
//	        JSONObject jsonObject = new JSONObject();
//	        jsonObject.put("title", post.getTitle());
//	        jsonObject.put("content", post.getContent());
//	        // 필요한 다른 속성도 추가
//	        
//	        jsonArray.add(jsonObject);
//	    }
//	    
//	    // 응답 설정
//	    response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    response.getWriter().write(jsonArray.toJSONString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
