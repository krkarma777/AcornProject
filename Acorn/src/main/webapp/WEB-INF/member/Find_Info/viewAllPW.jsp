<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- 모든 비밀번호를 출력하는 창(종료 창) -->

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 20px;
        text-align: center;
    }

    h1 {
        color: #333;
    }

    p {
        color: #555;
    }

    div {
        margin-top: 20px;
    }

    a {
        text-decoration: none;
        color: #007bff;
        font-weight: bold;
    }
</style>
</head>
<body>

<h1>이메일 발송 성공!</h1>

<%
MemberDTO dto = (MemberDTO) request.getAttribute("foundUserInfo");
%>

    <p><%=dto.getUserName()%>님의 비밀번호는  
    <%=dto.getUserEmailId()+"@"+dto.getUserEmailDomain()%>로 전송되었습니다.</p> 
    <p>확인 후 로그인 부탁드립니다.</p>

<div id="sitesShortCut">
    <a href="<%=request.getContextPath()%>/Login">로그인</a> 
</div>

</body>
</html>
