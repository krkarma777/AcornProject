package com.controller.member.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MemberService;

//회원가입 3단계의 자식창에서 아이디 중복 여부를 확인하는 비동기 처리
@WebServlet("/AjaxIDDuplicateServlet")
public class AjaxIDDuplicateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberService serv = new MemberService();
        PrintWriter out = response.getWriter();

        try {
            String userId = request.getParameter("userId");
            boolean isDuplicate = serv.isUserIdDuplicate(userId);

            //아이디와 일치하는 DB 정보가 있는 경우, 중복 출력
            if (isDuplicate) {
                out.print("duplicate");
            } 
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}