package com.controller.adminpage.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.AdminRprtdDTO;
import com.service.adminpage.AdminReportService;

/**
 * Servlet implementation class AdminMemberReported
 */
@WebServlet("/AdminMemberReported")
public class AdminMemberReported extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberReported() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.확인 
		System.out.println("in AdminMemberReported");
		
		
		//2.검색기준, 검색값 파싱 후 매핑
		HashMap<String, String> map = new HashMap<>();
		map.put("Criteria", request.getParameter("criteria"));
		map.put("SearchValue", request.getParameter("SearchValue"));
		
		//3.데이터베이스 접근 후 리스트 반환
		List<AdminRprtdDTO> list = null;
		AdminReportService service  = new AdminReportService();
		list = service.ReportedMemList(map);
		request.setAttribute("list", list);
		
		//4.포워딩
		RequestDispatcher dis = request.getRequestDispatcher("AdminPage/AdminPageMemRprtedMem.jsp");
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
