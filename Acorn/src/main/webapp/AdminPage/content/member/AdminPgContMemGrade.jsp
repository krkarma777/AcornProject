<%@ page import = "java.util.*" %>
<%@ page import = "com.dto.AdminMemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">
	<%List<AdminMemberDTO> list = (List<AdminMemberDTO>)session.getAttribute("list");
	System.out.println("in member" + list);%>
</script>
</head>
<body>
관리자페이지 회원등급관리Content
<hr>
<form action = "../AdminMemberServlet">
	<select name = "SearchCondition">
		<option value = "userid" class = "SearchStandard">회원ID</option>
		<option value = "nickName" class = "SearchStandard">회원닉네임</option>
		<option value = "signdate" class = "SearchStandard">가입일</option>
		<option value = "" class = "SearchStandard">상태</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	<hr>
	<table border = "1">
		<tr>
			<th>회원ID</th>
			<th>회원닉네임</th>
			<th>등급</th>
			<th>조치</th>
		</tr>
		<%
		if (list ==null){
		%>
		<tr>
		<td colspan = "5">검색조건을 입력하십시오</td>
		</tr>
		<%
		}else{
			for(AdminMemberDTO dto : list){
		%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			<%} %>
		<%} %>
		<%session.removeAttribute("list"); %>	
	</table>
</form>
</body>
</html>