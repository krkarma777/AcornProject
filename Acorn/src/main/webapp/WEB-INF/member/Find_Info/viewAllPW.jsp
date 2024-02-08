<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- 모든 비밀번호를 출력하는 창(종료 창) -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/ID.css">

<meta charset="UTF-8">
<title>Insert title here</title>

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
