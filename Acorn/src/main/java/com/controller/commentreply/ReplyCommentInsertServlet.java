package com.controller.commentreply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CommentDTO;
import com.service.CommentService;

/**
 * Servlet implementation class ReplyCommetInsertServlet
 */
@WebServlet("/ReplyCommentInsertServlet")
public class ReplyCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(" 일단 왔음 ");
		Long postId = Long.parseLong(request.getParameter("postId"));
		String aboveComId = request.getParameter("aboveComId");
		String userId = request.getParameter("userId").trim();
		String comText = request.getParameter("comText").trim();
		String nickname = request.getParameter("nickname").trim();
		
		//System.out.println("안녕"+nickname); //값 잘 나오는지 확인했음
		
		CommentService service = new CommentService();
		HttpSession session = request.getSession();
		
		//1. comment DB에 내용 저장
		CommentDTO commentDB = new CommentDTO(0, postId, userId, null, comText, nickname, 0, aboveComId);
		//comid, postid, userid, comdate, comtext, abovecom, aboveComId  로 생성
		
		int recordCount = service.AddCommnet(commentDB); //생성된 commentDB객체로 add(insert하고 있음)
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
