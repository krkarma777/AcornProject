<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입 2단계에서 기존 유저임이 확인되면 나오는 페이지 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/ID.css">

<head>
<meta charset="UTF-8">
<title>Found ID</title>

</head>

<body>

	<%
	MemberDTO dto = (MemberDTO) request.getAttribute("foundUser");
	%>

	<h2>찾은 유저 정보</h2>
	<p><%=dto.getUserName()%>님은 기존 회원이십니다.<br>
		아이디:	<%=dto.getUserId()%><br>
		가입일:	<%=dto.getUserSignDate()%>
	</p>
	<div>
		<a href="<%=request.getContextPath()%>/Login">로그인</a> | 
		<a href="<%=request.getContextPath()%>/FindPW">비밀번호 찾기</a>
	</div>
</body>

</html>
