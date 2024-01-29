package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String nextPage = "WEB-INF/main.jsp";
		
		if(category != null) {
		    nextPage = categoryDispatcher(category);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);//main.jsp로 이동
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String categoryDispatcher(String category) {
	    switch(category) {
	        case "movie":
	            return "WEB-INF/MovieDetail.jsp";
	        case "book":
	            return "WEB-INF/BookDetail.jsp";
	        case "tv":
	            return "WEB-INF/TvDetail.jsp";
	        default:
	            return "WEB-INF/main.jsp"; 
	    }
	}


}
