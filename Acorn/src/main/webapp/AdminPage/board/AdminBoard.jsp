<%@ page import = "java.util.*" %>
<%@ page import = "adminpage.dto.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type = text/javascript>
<% 
List<BoardDTO> list = (List<BoardDTO>)session.getAttribute("list");
//System.out.println("in AdminBoard.jsp:" + list);
%>
</script>
</head>
<body>
관리자페이지 게시판관리Content
<hr>
<form action = "../AdminBoardServlet">
	<select name = "SearchCondition">
		<option value = "POSTTITLE" class = "SearchStandard">제목</option>
		<option value = "USERID" class = "SearchStandard">작성자</option>
		<option value = "POSTDATE" class = "SearchStandard">작성일</option>
	</select>
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	
	<hr>
	<table border = "1" width = "70%">
		<tr>
			<th width = "10%">게시판명</th>
			<th width = "55%">글 제목</th>
			<th width = "15%">작성자</th>
			<th width = "15%">작성일</th>
			<th width = "15%">조치</th>
		</tr>
		<%
		
		if (list == null){
		%>
			<tr>
				<td colspan = "5" align = "center">검색조건을 입력하세요.</td>
			</tr>
		<%
		}else{
				for(BoardDTO dto : list){
		%>
					<tr>
						<td><%=dto.getPostboard() %></td>
						<td><a href = "#"><%=dto.getPosttitle() %></a></td>
						<td><a href = "#"><%=dto.getUserid() %></a></td>
						<td><%=dto.getPostdate() %></td>
						<td>
							<button id = "DelButton">삭제</button>
							<button calss = "SuspendButton">게시중단</button>
						</td>
					</tr>
			
			<%} %><!-- for -->
			<%} %><!-- if else -->
		<%session.removeAttribute("list"); %>
	</table>
</form>

</body>
</html>