package com.controller.adminpage.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.BoardAdminDTO;
import com.service.adminpage.BoardService;

/**
 * Servlet implementation class AdminBoardServlet
 */
@WebServlet("/AdminBoardServlet")
public class AdminBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("adminboard");
		
		//parsing
		String SearchValue = request.getParameter("SearchValue");
		String SearchCondition = request.getParameter("SearchCondition");
		
		HttpSession session = request.getSession();
		
		if(SearchValue!=null) {
		BoardService service = new BoardService();
		HashMap<String, String> map = new HashMap<>();
		
		map.put("SearchCondition", SearchCondition);
		map.put("SearchValue", SearchValue);
		
		System.out.println(map);
		
		List<BoardAdminDTO> list = null;
		list = service.SearchPost(map);
		System.out.println(list);
	
		session.setAttribute("list", list);
		}
		
		//RequestDispatcher dis = request.getRequestDispatcher("./AdminPage/AdminPageBoard.jsp");
		//dis.forward(request, response);
		
		response.sendRedirect("./AdminPage/AdminPageBoard.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("AdminBoard.jsp");
	}

}
