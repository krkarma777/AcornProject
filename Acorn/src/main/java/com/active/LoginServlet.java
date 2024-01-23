package com.active;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;

//로그인 시스템 스타팅 포인트
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Login/loginMain.jsp");

		// ************************************전체 비밀번호 출력 기능을 사용하는 경우***************************************	//
		// 1 이메일 시스템 작동을 위해 본인의 이메일 아이디와 2차 인증 번호를 com.mail.SendMail에 입력해주세요.							//
		// 2. 수신자는 현재 테스트용 계정에 연결된 상태입니다. 실제 구동을 위해서는 com.mail.SendMailServlet에 존재하는 이메일에 연결해주세요		//
		// 3. Servlet_search_all_PW_by_answer의 RequestDispatcher 수신처를 바꾸어주세요. 현재는 테스트 모드 상태입니다.				//
		// ********************************************************************************************************	//
		
	}
}
