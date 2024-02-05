package com.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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
		
		String how = request.getParameter("How");
		System.out.println(how+"   how값 확인");
		
		
		if(how!=null) {
			
			if(("0").equals(how)) { //delteUpdate start. 삭제버튼 눌렸을 때 기능하는 것 
				// ajax로 넘어온 data 가져왔음
				int comId = Integer.parseInt(request.getParameter("comId"));
				//System.out.println("deleteUpdate 서블릿에서의 comid"+comid);
				
				int num =service.deleteUpdateComment(comId);
				//삭제 기능
				if(num==0) {
				
						out.print("댓글을 삭제할 수 없습니다.");
					
				}else {
					out.print("댓글이 삭제되었습니다.");
				} //delteUpdate end // //댓글이 삭제되었으면 num이 0보다 클테니, 그럴경우 else의 text를 / 반대의 경우 if의 text를 ajax로 응답하고 있음
				
			}//if(("0").equals(how)) end
			
			if(("1").equals(how)) { //update start. 수정버튼 눌렸을 때 기능하는 것
			
				String comId = request.getParameter("comId");
				String comText = request.getParameter("comText");
				System.out.println("업데이트확인용" +comId+"    "+comText );
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("comId", comId);
				map.put("comText", comText);
				
				int num =service.updateComment(map);
				
				//수정 기능
				
				if(num==0) {
					
					out.print("댓글을 수정 할 수 없습니다.");
				
				}else {
					out.print("댓글이 수정되었습니다.");
				}//update end 
				
		
			}//if(("1").equals(how))  end
		
		}//if(how!=null) end
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
