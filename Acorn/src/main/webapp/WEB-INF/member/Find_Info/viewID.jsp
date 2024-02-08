<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 아이디 찾기에서 아이디를 찾을 경우, 나오는 페이지의 jsp -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/ID.css">

<head>
<meta charset="UTF-8">
<title>Found ID</title>

</head>

<body>

	<%
	MemberDTO dto = (MemberDTO) request.getAttribute("found_userId");
	%>

	<h2>찾은 아이디 정보</h2>
	<div><%=dto.getUserName()%>님의 아이디는
		<%=dto.getUserId()%>입니다.
	</div>
	<div id="sitesShortCut">
		<a href="<%=request.getContextPath()%>/Login">로그인</a> | <a href="<%=request.getContextPath()%>/FindPW">비밀번호 찾기</a>
	</div>
</body>

</html>
