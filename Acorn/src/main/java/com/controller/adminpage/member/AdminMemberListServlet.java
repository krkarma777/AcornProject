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
import javax.servlet.http.HttpSession;

import com.dto.AdminMemberDTO;
import com.service.adminpage.AdminMemberService;

/**
 * Servlet implementation class AdminMemberServlet
 */
//@WebServlet("/AdminMemberGradeServlet")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("memberservlet");
		HttpSession session = request.getSession();
		String SearchValue = request.getParameter("SearchValue");
		String SearchCriteria = request.getParameter("SearchCriteria");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("SearchCriteria", SearchCriteria);
		map.put("SearchValue", SearchValue);
		
		
		if(SearchValue.length()!=0) {
			AdminMemberService service = new AdminMemberService();
			
			List<AdminMemberDTO> list = service.SearchMember(map);
			session.setAttribute("list", list);

			}
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
