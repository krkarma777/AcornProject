<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 디버그를 위한 로그인 성공하면 나오는 페이지 -->

<head>
<meta charset="UTF-8">
<title>로그인 회원 정보 페이지</title>

 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            height: 100vh;
        }

        h1 {
            color: #333;
        }

        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>


</head>
<body>

	<h1>[회원 정보]</h1>
	<hr>

	<%
	MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");
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
	</table>
	<button onclick="location.href='<%=request.getContextPath()%>/Login'">로그인폼</button>
	<button onclick="location.href='<%=request.getContextPath()%>/Logout'">로그아웃</button>
	<br>

</body>
</html>