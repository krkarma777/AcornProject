package com.controller.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;

import com.service.MyPageService;

@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberUpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	HttpSession session = request.getSession();
        MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
		String nextPage=null;
		System.out.println(dto);
    	if(dto!=null) {
    	request.setCharacterEncoding("utf-8");//한글처리 
    	String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        String userName = request.getParameter("userName");
        String userGender = request.getParameter("userGender");
        String nickname = request.getParameter("nickname");

        // 전화번호 및 이메일 정보 처리
        String userPhoneNum1 =request.getParameter("userPhoneNum1");
        String userPhoneNum2 = request.getParameter("userPhoneNum2");
        String userPhoneNum3 = request.getParameter("userPhoneNum3");
        int userSSN1 = Integer.parseInt(request.getParameter("userSSN1"));
        int userSSN2 = Integer.parseInt(request.getParameter("userSSN2"));

        String userEmailId = request.getParameter("userEmailId");
        String userEmailDomain = request.getParameter("userEmailDomain");
        String userSignDate = request.getParameter("userSignDate");
        String userType = request.getParameter("userType");

        MemberDTO dto2 = 
        		new MemberDTO(userId, userPw, userName, userSSN1, userSSN2,  nickname,
    			userGender,  userPhoneNum1,  userPhoneNum2,  userPhoneNum3, userEmailId,
    			 userEmailDomain,  userSignDate,  userType);
System.out.println(dto2);
        MyPageService service = new MyPageService();
        int num = service.updateMember(dto2);
        System.out.println(num);
        if(num==1) {
			dto2= service.mypage(userId);
			session.setAttribute("loginUser", dto2);// 세션에 최신정보 저장 
			session.setAttribute("mesg", "회원정보가 수정되었습니다.");// 세션에 최신정보 저장 
		}	
        nextPage = "main";
    	}else {
			nextPage = "Login";
			request.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		  RequestDispatcher dis =
	    		  request.getRequestDispatcher(nextPage);
	      dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
