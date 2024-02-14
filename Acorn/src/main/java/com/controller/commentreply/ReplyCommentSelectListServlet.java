package com.controller.commentreply;

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
 * Servlet implementation class ReplyCommentSelectList
 */
@WebServlet("/ReplyCommentSelectListServlet")
public class ReplyCommentSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyCommentSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//abovecomid가 있는 댓글 중에서 넘어온 comId와 일치하는 레코드들을 가져올 거임
		
		List<CommentDTO> list = new ArrayList<>();
		String comId = request.getParameter("comId");
		CommentService service = new CommentService();
		list = service.replyComSelectAllBycomId(comId);
		
		HashMap<String,List<CommentDTO>> map = new HashMap<>();
		map.put("replyCommentDBList", list);
		
		
		//아래서부터 중요, map으로 저장된 데이터를 json형태로 바꾸기 위해 lib폴더에 jackson으로 시작하는 jar파일 3개가 추가되었고
		//아래 코드로 jar 활성화 시켜서 map -> json으로 했음. ajax로 map을 보내려면 이 방법뿐이었음
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//System.out.println("ReplyCommentSelectList"+json); //확인용
		out.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
