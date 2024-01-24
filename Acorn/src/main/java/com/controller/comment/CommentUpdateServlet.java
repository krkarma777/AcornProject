package com.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.CommentService;

@WebServlet("/CommentUpdateServlet")
public class CommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CommentService service = new CommentService();
		
		//ajax로 넘어온 data 가져왔음
		int comid = Integer.parseInt(request.getParameter("comid"));
		//System.out.println("업데이트 서블릿에서의 comid"+comid);
		
		int num =service.updateComment(comid);
		//삭제 기능
		if(num==0) {
		
				out.print("댓글을 삭제할 수 없습니다.");
			
		}else {
			out.print("댓글이 삭제되었습니다.");
		} //댓글이 삭제되었으면 num이 0보다 클테니, 그럴경우 else의 text를 / 반대의 경우 if의 text를 ajax로 응답하고 있음
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
