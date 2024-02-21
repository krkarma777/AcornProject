<%@ page import = "java.util.*" %>
<%@ page import = "com.dto.AdminRprtdDTO" %>
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


List<AdminRprtdDTO> list = (List<AdminRprtdDTO>)session.getAttribute("list");
//System.out.println("in AdminBoard.jsp:" + list);
%>
</head>
<body>
<h1>삭제된 게시글 관리</h1>
<hr>
<form action = "../AdminBoardServlet">
	<select name = "SearchCondition">
		<option value = "" class = "SearchStandard">글 아이디</option>
		<option value = "" class = "SearchStandard">글 제목</option>
		<option value = "" class = "SearchStandard">작성자</option>
		<option value = "" class = "SearchStandard">삭제사유</option>
		<option value = "" class = "SearchStandard">처분여부</option>
		<option value = "" class = "SearchStandard">삭제예정일</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	
	<hr>
	<table border = "1" width = "70%">
		<tr>
			<th >Post ID</th>
			<th >글 제목</th>
			<th >작성자</th>
			<th>삭제사유</th>
			<th>처분여부</th>
			<th >삭제예정일</th>
		</tr>
		<%
		

				if (list == null){
		%>
			<tr>
				<td colspan = "6" align = "center">검색조건을 입력하세요.</td>
			</tr>
		<%
		

				}else{
				for(AdminRprtdDTO dto : list){
		%>
					<tr>
						<td></td>
						<td><a href = "#"></a></td>
						<td><a href = "#"></a></td>
						<td></td>
						<td>
							<!-- <button class = "DelBtn">삭제</button> -->
						</td>
						<td></td>
					</tr>
			
			<%} %><!-- for -->
			<%} %><!-- if else -->
		<%session.removeAttribute("list"); %>
	</table>
</form>

</body>
</html>