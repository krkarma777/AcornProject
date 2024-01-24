<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 디버그를 위한 회원 리스트 출력 페이지의 jsp -->

<head>
<meta charset="UTF-8">
<title>회원 리스트(테스트용)</title>

<style>
    #for_Login {
        margin-top: 20px;
        text-align: center;
    }

    #loginForm {
        display: inline-block;
        border: 1px solid #ddd;
        padding: 10px;
        border-radius: 5px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    table {
        margin: 0 auto; /* 중앙 정렬 */
    }

    td {
        padding: 8px;
        text-align: left;
    }

    .loginSet {
        width: 200px; /* 입력 필드의 너비 조정 */
        padding: 5px;
        margin-right: 5px;
    }

    #Find_Member_One_button {
        padding: 8px 20px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
    }

    #Find_Member_One_button:hover {
        background-color: #0056b3;
    }

    #sitesShortCut, #debugLink {
        margin-top: 10px;
    }

    #sitesShortCut a, #debugLink a {
        text-decoration: none;
        color: #007BFF;
        font-weight: bold;
        margin-right: 10px;
    }

    #sitesShortCut a:hover, #debugLink a:hover {
        text-decoration: underline;
    }
</style>





</head>
<body>

	<h1>[회원 목록]</h1>
	<hr>

	<%
	List<MemberDTO> list = (List<MemberDTO>) session.getAttribute("memberList");
	%>

	<table border=1>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>SSN1</th>
				<th>SSN2</th>
				<th>성별</th>
				<th>핸드폰1</th>
				<th>핸드폰2</th>
				<th>핸드폰3</th>
				<th>이메일 아이디</th>
				<th>이메일 도메인</th>
				<th>가입일</th>
				<th>유형</th>
			</tr>
			<%
			for (MemberDTO dto : list) {
			%>
			<tr>
				<td><%=dto.getUserId()%></td>
				<td><%=dto.getUserPw()%></td>
				<td><%=dto.getUserName()%></td>
				<td><%=dto.getNickname()%></td>
				<td><%=dto.getUserSSN1()%></td>
				<td><%=dto.getUserSSN2()%></td>
				<td><%=dto.getUserGender()%></td>
				<td><%=dto.getUserPhoneNum1()%></td>
				<td><%=dto.getUserPhoneNum2()%></td>
				<td><%=dto.getUserPhoneNum3()%></td>
				<td><%=dto.getUserEmailId()%></td>
				<td><%=dto.getUserEmailDomain()%></td>
				<td><%=dto.getUserSignDate()%></td>
				<td><%=dto.getUserType()%></td>
			</tr>
			<%
			}
			%>
	</table>
	
	<div id="for_Login">
        <form id="loginForm" action="<%=request.getContextPath()%>/memberOneServlet" method="post">
            <table>
                <tr>
                    <td>아이디:</td>
                    <td><input type="text" id="userId" name="userId" class="loginSet"></td>
                    <td>
                        <input id="Find_Member_One_button" type="submit" value="확인">
                    </td>
                </tr>
            </table>
        </form>
        <div id="sitesShortCut">
            <a href="<%=request.getContextPath()%>/Login">로그인 폼</a>
        </div>
    </div>
</body>
</html>