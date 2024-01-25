package com.controller.adminpage.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberAdminDTO;
import com.service.adminpage.MemberService;

/**
 * Servlet implementation class AdminMemberServlet
 */
@WebServlet("/AdminMemberServlet")
public class AdminMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberServlet() {
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
		
		if(SearchValue!=null) {
			MemberService service = new MemberService();
			
			List<MemberAdminDTO> list = service.SearchMember(SearchValue);
			session.setAttribute("list", list);

			}
		//RequestDispatcher dis = request.getRequestDispatcher("AdminPage/AdminPageMember.jsp");
		//dis.forward(request, response);
		
		response.sendRedirect("./AdminPage/AdminPageMember.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
