<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dto.memberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입 2단계에서 기존 유저임이 확인되면 나오는 페이지 -->

<head>
<meta charset="UTF-8">
<title>Found ID</title>

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

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
            margin-bottom: 10px;
        }

        a {
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            font-size: 16px;
            margin-top: 20px;
            display: inline-block;
        }

        a:hover {
            background-color: #0056b3;
        }
    </style>

</head>

<body>

	<%
	memberDTO dto = (memberDTO) request.getAttribute("foundUser");
	%>

	<h2>찾은 유저 정보</h2>
	<p><%=dto.getUserName()%>님은 기존 회원이십니다.<br>
		아이디:	<%=dto.getUserId()%><br>
		가입일:	<%=dto.getUserSignDate()%>
	</p>
	<div>
		<a href="<%=request.getContextPath()%>/LoginServlet">로그인</a> | 
		<a href="<%=request.getContextPath()%>/PWServlet">비밀번호 찾기</a>
	</div>
</body>

</html>
