package com.controller.member.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;
import com.service.memberService;

@WebServlet("/AjaxMatchQnAServlet")
public class AjaxMatchQnAServlet extends HttpServlet {
       
	//전체 비밀번호 출력을 하는 자식창에서 사용하는 비동기처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
		
        PrintWriter out = response.getWriter();

        try {
        	String confirmUserInfo = request.getParameter("userInfo");
        	String userAnswer = request.getParameter("answer");
             String userId = request.getParameter("userId");
             boolean can_All_PW = false;
																		             //디버그 코드*****************************
																		             System.out.println(confirmUserInfo);
																		             System.out.println(userAnswer);
																		             System.out.println(userId);
																		             //*************************************
			 // 선택된 질문에 따라 사용되는 Method 변경**************************
             if (confirmUserInfo.equals("nickname")) {
                 can_All_PW = serv.findPWbyNickname(userAnswer, userId);
             } else if (confirmUserInfo.equals("userPhoneNum")) {
                 can_All_PW = serv.findPWbyPhoneNum(userAnswer, userId);
             } else if (confirmUserInfo.equals("userEmail")) {
                 can_All_PW = serv.findPWbyEmail(userAnswer, userId);
             }
             // 선택된 질문에 따라 사용되는 Method 변경**************************
             																		//디버그 코드*****************************                 
             																		System.out.println(can_All_PW);    
             																		//*************************************
             //사용자 ID와 질문과 답변이 일치할 경우, ajax출력
             if (can_All_PW == true) {
        		out.print("correct_Answer");
        
            //사용자 ID와 질문과 답변이 일치하지 않을 경우, ajax출력																			
            } else if (can_All_PW == false){
                out.print("wrong_Answer");
            }
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}