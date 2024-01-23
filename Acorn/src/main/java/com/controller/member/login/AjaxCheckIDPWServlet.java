package com.controller.member.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.memberService;

//로그인 메인창에서 사용하는 로그인 가능 여부룰 판단하는 비동기 처리
@WebServlet("/AjaxCheckIDPWServlet")
public class AjaxCheckIDPWServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
        PrintWriter out = response.getWriter();

        //유저 아이디와 패스워드가 DB정보와 일치하는지 검증
        try {
        	String userId = request.getParameter("userId");
        	String userPw = request.getParameter("userPw");

            boolean canLogin = serv.loginPossible(userId, userPw);

            //일치할 경우
            if (!canLogin) {
            	out.print("loginFail");                
            }
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}