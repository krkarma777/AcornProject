package com.controller.member.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.memberDTO;
import com.service.memberService;

//회원가입 3단계에서 이메일 중복 여부를 확인하는 비동기 처리
@WebServlet("/AjaxEmailDuplicateServlet")
public class AjaxEmailDuplicateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
        PrintWriter out = response.getWriter();

        try {
        	String userEmailId = request.getParameter("userEmailId");
        	String userEmailDomain = request.getParameter("userEmailDomain");
        	
            boolean isDuplicate = serv.isUserEmailDuplicate(userEmailId, userEmailDomain);

            //이메일 전체와 일치하는 DB 정보가 있는 경우, 중복 출력
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