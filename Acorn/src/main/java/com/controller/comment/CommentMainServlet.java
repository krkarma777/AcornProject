package com.controller.comment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CommentDTO;
import com.service.CommentService;


@WebServlet("/MainServlet")
public class CommentMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글 메인창 구현을 위해 필요한 데이터를 미리 scope에 저장해서 최초 실행하는 Main서블릿//
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		
		//userid 로그인 시 저장 된 session정보 받아오기. 현재는 임시로 지정했음
		session.setAttribute("userid", "kmj3718");
		//게시글 번호를 가져와야 하는데, session이나 다른 곳에 저장된 "현재 접속된 게시글의 번호"를 가져와야함. 일단 임시로 게시글번호 지정하겠음
		session.setAttribute("postid", 1);
		
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/board/commentMain.jsp");
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
