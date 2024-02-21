package com.controller.adminpage.post;

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
 * Servlet implementation class AdminBoardServlet
 */
@WebServlet("/AdminPostReported")
public class AdminPostReported extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPostReported() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in AdminPostReported Servlet");
		
		//parsing
		String SearchValue = request.getParameter("SearchValue");
		System.out.println(SearchValue);
		
		String criteria = request.getParameter("criteria");
		System.out.println(criteria);
		
		
		
			
		HashMap<String, String> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("SearchValue", SearchValue);
		
		System.out.println(map);
		
		AdminReportService service = new AdminReportService();
		
		List<AdminRprtdDTO>list = service.SearchReport(map);
		
		System.out.println("in AdminPostReported");
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		
		String url = request.getContextPath();
		System.out.println(url);
		String nextPage = "AdminPage/AdminPagePostRprtedPost.jsp";
		System.out.println(nextPage);
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("AdminBoard.jsp");
	}

}
