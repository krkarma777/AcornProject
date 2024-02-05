package com.controller.member.register;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

//회원가입 3단계 페이지에서 사용
@WebServlet("/InsertData")
public class InsertUserDataServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberService serv = new MemberService();

		HttpSession session = request.getSession();
		//term 관련 request.getParameter가 올 수 있음(미정)
		

		// 입력한 정보가 타당하지 않으면 false로 전환
		boolean failMesg = true;

		// 아이디 검증
		String userId = request.getParameter("userId");
		boolean isDuplicateID = serv.isUserIdDuplicate(userId);

		if (userId.length() < 4) { // 아이디 길이 규격확인
			System.out.println("아이디 길이 오류 " + userId + " " + userId.length());
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "아이디 길이가 규정에 맞지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else if (isDuplicateID) { // 아이디 중복여부 재확인
			System.out.println("아이디 중복");
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "이미 가입된 아이디입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);
		} else { // 아이디 규격 통과
			System.out.println("아이디 확인");
		}

		// 비밀번호 검증
		String userPw = request.getParameter("userPw");
		String userPwConfirm = request.getParameter("userPwConfirm");

		if (!(userPw.equals(userPwConfirm))) { // 비밀번호와 비밀번호 재확인 번호 일치 확인
			System.out.println("비밀번호 일치 오류 " + userPw + " " + userPwConfirm);
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "비밀번호가 일치하지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else if (userPw.length() < 6) { // 비밀번호 길이 규격확인
			System.out.println("비밀번호 길이 오류 " + userPw + " " + userPw.length());
			System.out.println("회원 가입 실패");
			request.setAttribute("mesg", "비밀번호 길이가 규정에 맞지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else { // 비밀번호 규격 통과
			System.out.println("비밀번호 확인");
		}

		// 이름 및 SSN 검증
		String userName = request.getParameter("userName").trim();
		String userSSN1 = request.getParameter("userSSN1").trim();
		String userSSN2 = request.getParameter("userSSN2").trim();
		MemberDTO foundUser = serv.findUserId(userName, userSSN1, userSSN2);
		int ssn2FirstNum = Integer.parseInt(String.valueOf(userSSN2).substring(0, 1));

		if (foundUser != null) { // 이름 + SSN이 모두 일치하는 유저가 있는지 확인
			System.out.println("이름, SSN 기존 회원 정보 있음");
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "이미 가입된 이름과 주민등록번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else { // 이름 + SSN 규격 통과
			System.out.println("이름, SSN 확인");
		}

		// 닉네임 검증
		String nickname = request.getParameter("nickname");
		boolean isDuplicateNickname = serv.isUserNicknameDuplicate(nickname);

		if (nickname.length() < 2) { // 닉네임 길이 규격 확인
			System.out.println("닉네임 길이 오류 " + nickname + " " + nickname.length());
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "닉네임 길이가 규정에 맞지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else if (isDuplicateNickname) { // 닉네임 중복 여부 확인
			System.out.println("닉네임 중복");
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "이미 가입된 닉네임입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else { // 닉네임 규격 통과
			System.out.println("닉네임 확인");
		}

		// 성별 검증
		String userGender = request.getParameter("userGender");
		if (!(userGender.equals("male") || userGender.equals("female"))) { // 성별 확인
			System.out.println("성별 오류 " + userGender);
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "있을 수 없는 성별입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else { // 성별 규격 통과
			System.out.println("성별 확인");
		}

		// 핸드폰 번호 검증
		String userPhoneNum1 = request.getParameter("userPhoneNum1");
		String userPhoneNum2 = request.getParameter("userPhoneNum2");
		String userPhoneNum3 = request.getParameter("userPhoneNum3");
		boolean isDuplicatePN = serv.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);

		if (isDuplicatePN) { // 핸드폰 번호 중복 확인(모든 번호 일치)
			System.out.println("핸드폰 번호 중복 " + userPhoneNum1 + " - " + userPhoneNum2 + " - " + userPhoneNum3);
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "이미 가입된 핸드폰 번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else if (userPhoneNum1.length() != 3 || userPhoneNum2.length() != 4 || userPhoneNum3.length() != 4) { // 핸드폰
																												// 번호 길이
																												// 확인
			System.out.println("핸드폰 번호 오류 " + userPhoneNum1 + " - " + userPhoneNum2 + " - " + userPhoneNum3);
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "있을 수 없는 핸드폰 번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else { // 핸드폰 번호 규격 통과
			System.out.println("핸드폰 번호 확인");
		}

		// 이메일 검증
		String userEmailId = request.getParameter("userEmailId");
		String userEmailDomain = request.getParameter("userEmailDomain");
		boolean isDuplicateEM = serv.isUserEmailDuplicate(userEmailId, userEmailDomain);

		if (isDuplicateEM) { // 이메일 중복 확인(이메일 아이디 + 이메일 도메인이 모두 일치)
			System.out.println("이메일 중복 " + userEmailId + " @ " + userEmailDomain);
			System.out.println("회원 가입 실패");
			failMesg = false;
			request.setAttribute("mesg", "이미 가입된 이메일입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
			dis.forward(request, response);

		} else { // 이메일 규격 통과
			System.out.println("이메일 확인");
		}

		// 가입일 - 가입한 당일 날짜
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String userSignDate = dateFormat.format(currentDate);

		// 모든 규격을 통과한 경우, insert 진행
		// userType(회원 등급)은 1(일반 멤버)로 고정
		if (failMesg) {

			MemberDTO dto = new MemberDTO(userId, userPwConfirm, userName, userSSN1, userSSN2, userGender, nickname, userPhoneNum1, userPhoneNum2, userPhoneNum3, userEmailId, userEmailDomain, userSignDate, "1");
			System.out.println(dto);
			int num = serv.insertNewMember(dto);

			// 성공적으로 insert된 경우, 회원가입 성공 페이지로 이동
			if (num == 1 && failMesg == true) {
				System.out.println("회원가입 성공");
				RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerSuccess.jsp");
				dis.forward(request, response);

				// 모든 데이터가 규격을 통과했음에도 insert되지 않았을 경우, 회원가입 실패 페이지로 이동
			} else {
				System.out.println("회원가입 실패");
				request.setAttribute("mesg", "모종에 이유로 가입에 실패했습니다. 다시 한번 해주세요");
				RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Register/registerFailure.jsp");
				dis.forward(request, response);
			}
		}

	}

}
