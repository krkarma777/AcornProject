package com.controller.member.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MemberService;

//회원가입 3단계에서 핸드폰 번호 중복 여부를 확인하는 비동기 처리
@WebServlet("/AjaxPhoneNumDuplicateServlet")
public class AjaxPhoneNumDuplicateServlet extends HttpServlet {
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberService serv = new MemberService();
		
        PrintWriter out = response.getWriter();

        try {
        	String userPhoneNum1 = request.getParameter("userPhoneNum1");
        	String userPhoneNum2 = request.getParameter("userPhoneNum2");
        	String userPhoneNum3 = request.getParameter("userPhoneNum3");

            boolean isDuplicate = serv.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);

            //핸드폰 번호 전체와 일치하는 DB 정보가 있는 경우, 중복 출력
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