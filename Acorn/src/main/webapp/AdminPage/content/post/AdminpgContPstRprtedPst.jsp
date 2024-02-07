<%@ page import = "java.util.*" %>
<%@ page import = "com.dto.AdminBoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type = text/javascript>
	
</script>
<%
List<AdminBoardDTO> list = (List<AdminBoardDTO>)session.getAttribute("list");
//System.out.println("in AdminBoard.jsp:" + list);
%>
</head>
<body>
<h1>신고된 게시글</h1>
<hr>
<form action = "../AdminBoardServlet">
	<select>
		<option value = "">게시글</option>
		<option value = "">댓글</option>
		<option value = "">전체</option>
	</select>
	<select name = "SearchCondition">
		<option value = "POSTTITLE" class = "SearchStandard">제목</option>
		<option value = "USERID" class = "SearchStandard">작성자</option>
		<option value = "POSTDATE" class = "SearchStandard">작성일</option>
	</select>
	
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	
	<hr>
	<table border = "1" >
		<tr>
			<th rowspan="2">신고번호</th>
			<th rowspan="2">신고된 글 번호</th>
			<th rowspan="2">신고자</th>
			<th rowspan="2">작성자</th>
			<th colspan="5">신고사유</th>
			<th rowspan="2">신고내용</th>
		</tr>
		<tr>
			<th>음란물</th>
			<th>언어</th>
			<th>도배</th>
			<th>규정위반</th>
			<th>기타</th>
		</tr>
		<%
		if (list == null){
		%>
			<tr>
				<td colspan = "10" align = "center">검색조건을 입력하세요.</td>
			</tr>
		<%
		}else{
				for(AdminBoardDTO dto : list){
		%>
					<tr>
						<td></td>
						<td><a href = "#"></a></td>
						<td><a href = "#"></a></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
			
			<%} %><!-- for -->
			<%} %><!-- if else -->
	</table>
</form>

</body>
</html>