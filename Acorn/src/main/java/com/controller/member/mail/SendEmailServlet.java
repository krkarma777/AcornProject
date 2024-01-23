package com.controller.member.mail;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberDTO dto = (memberDTO)request.getAttribute("foundUserInfo") ;
		
		String userEmail = dto.getUserEmailId()+"@"+dto.getUserEmailDomain();
		String userName = dto.getUserName();
		String userid = dto.getUserId();
		String userPW = dto.getUserPw();
																							//디버그 코드********************
																							System.out.println(userEmail);
																							//디버그 코드********************
		// 보내는 사람,제목, 내용, 받는 사람 설정
		String host = "smtp.naver.com";
		String subject = "AcornTP 비밀번호 전체 발송 메일";		 	// 메일제목
		String from = "이메일";				 					// 보내는 메일주소(2차 인증한 아이디 메일)
		String fromName = "AcornTP 문화 관리자"; 				 	// 보내는 사람이름
		String to = userEmail; 									// 받는 메일주소		
		String content = userName+"님! 회원님의 "
				+ "아이디는 "+ userid
				+ ", 비밀번호는 " + userPW + "입니다. 감사합니다."; 	// 메일 내용

		try {
			// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			// 네이버 SMTP 사용시

			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);

			// 2단계 인증**************************************
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.trust", "*");
			props.put("mail.smtp.ssl.enable", "false");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			// 2단계 인증**************************************

			props.put("mail.smtp.port", "465"); // 보내는 메일 포트 설정(도메인 별로 포트번호가 다름)
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Authenticator auth = new SendEmail();
			Session mailSession = Session.getDefaultInstance(props, auth);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 보내는 사람 설정
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address); 	// 받는 사람설정
			msg.setSubject(subject); 								// 제목설정
			msg.setSentDate(new java.util.Date()); 					// 보내는 날짜 설정
			msg.setContent(content, "text/html; charset=EUC-KR"); 	// 내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); // 메일 보내기

		} catch (MessagingException ex) {
			System.out.println("mail send error : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("Find_Info/viewAllPW.jsp");
		dis.forward(request, response);

	}
}
