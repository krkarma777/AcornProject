package com.controller.adminpage.post;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.adminpage.AdminReportService;

/**
 * Servlet implementation class AdminBoardDeleteServlet
 */
@WebServlet("/AdminPostDeleteServlet")
public class AdminPostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPostDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		System.out.println("in AdminPostDeleteServlet");
		
		//전송 데이터 arrayList로 저장
		String data = request.getParameter("postArr");
		String[] postArr = data.split(",");
		List<String> list = Arrays.asList(postArr);
		
		System.out.println(list);
		
		//service 레이어 전달
		AdminReportService service = new AdminReportService();
		int n = service.delReportedPost(list);
		
		//포워딩할 변수 설정
		//삭제 메세지
		request.setAttribute("mesg", n + "개의 신고글이 삭제처리 되었습니다.");
		//삭제 후 리스트
		//포워딩
		RequestDispatcher dis = request.getRequestDispatcher("/AdminPostReported");
		dis.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
