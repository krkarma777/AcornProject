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

import com.dto.AdminMemberDTO;
import com.service.adminpage.AdminMemberService;

/**
 * Servlet implementation class AdminMemberGradeServlet
 */
@WebServlet("/AdminMemberGradeServlet")
public class AdminMemberGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberGradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in AdminMemberGradeServlet");
		
		String criteria = request.getParameter("criteria");
		String SearchValue = request.getParameter("SearchValue");
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("SearchValue", SearchValue);
		
		AdminMemberService service = new AdminMemberService();
		List<AdminMemberDTO> list = service.SearchMember(map);
		
		request.setAttribute("list", list);
		RequestDispatcher dis = request.getRequestDispatcher("/AdminPage/AdminPageMemGrade.jsp");
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
