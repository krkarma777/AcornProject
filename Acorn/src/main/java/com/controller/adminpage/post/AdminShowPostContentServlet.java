package com.controller.adminpage.post;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.k;

/**
 * Servlet implementation class AdminShowPostContentServlet
 */
@WebServlet("/AdminShowPostContentServlet")
public class AdminShowPostContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminShowPostContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    public void asd() {};
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in AdminShowPostContentServlet");
		String str = request.getParameter("postid");
		//System.out.println(str);
		String postid = str.substring(1);
		//System.out.println(postid);
		String cat = str.substring(0,1);
		//System.out.println(cat);
		
		
		switch (cat) {
		case "p": 
			System.out.println("post case");
			
			
		case "r": 
			System.out.println("review case");
		case "c": 
			System.out.println("comment case");
			
			break;

		default:
			break;
		}
		//PostService service = new PostService();
		//PostDTO pdto = service.select(postid);
		
		//request.setAttribute("pdto", pdto);
		
		//RequestDispatcher dis = request.getRequestDispatcher("");
		//dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
