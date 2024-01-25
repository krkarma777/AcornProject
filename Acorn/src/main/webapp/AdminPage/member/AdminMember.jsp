<%@ page import = "java.util.*" %>
<%@ page import = "adminpage.dto.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">
	<%
	List<MemberDTO> list = (List<MemberDTO>)session.getAttribute("list");
	System.out.println("in member" + list);
	%>
</script>
</head>
<body>
관리자페이지 회원관리Content
<hr>
<form action = "../AdminMemberServlet">
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue"><input type="submit" value = "검색"> 
	
	<hr>
	<table border = "1">
		<tr>
			<th>회원ID</th>
			<th>회원닉네임</th>
			<th>가입일</th>
			<th>조치</th>
		</tr>
		<%if (list ==null){ %>
		<tr>
		<td colspan = "4">검색조건을 입력하십시오</td>
		</tr>
		<%}else{
			for(MemberDTO dto : list){%>
				<tr>
					<td><%=dto.getUserid() %></td>
					<td><%=dto.getNickname() %></td>
					<td><%=dto.getUsersigndate() %></td>
					<td><input type = "button" value = "정지"></td>
					<td><input type = "button" value = "강퇴"></td>
				</tr>
			<%} %>
		<%} %>
		<%session.removeAttribute("list"); %>	
	</table>
</form>
</body>
</html>