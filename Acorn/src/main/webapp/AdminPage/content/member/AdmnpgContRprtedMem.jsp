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
관리자페이지 신고된 회원관리 Content
<hr>
<form action = "#">
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
			<th rowspan = "2">신고번호</th>
			<th rowspan = "2">신고대상</th>
			<th rowspan = "2">신고자</th>
			<th colspan = "5">신고사유</th>
			<th rowspan = "2">신고내용</th>
		</tr>
		<tr>
			<th>음란물</th>
			<th>언어</th>
			<th>도배</th>
			<th>규정위반</th>
			<th>기타</th>
		</tr>
		<%
		if (list ==null){
		%>
		<tr>
		<td colspan = "9">검색조건을 입력하십시오</td>
		</tr>
		<%
		}else{
			for(AdminMemberDTO dto : list){
		%>
				<tr>
					<td><a href = "#"></a></td>
					<td><%=dto.getNickname() %></td>
					<td></td>
					<td></td>
					<td><input type = "button" value = "정지"><input type = "button" value = "강퇴"></td>
				</tr>
			<%} %>
		<%} %>
		<%session.removeAttribute("list"); %>	
	</table>
</form>
</body>
</html>