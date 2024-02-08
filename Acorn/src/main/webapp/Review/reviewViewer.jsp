<%@page import="com.dto.ContentDTO"%>
<%@page import="com.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	<%
	ReviewDTO review = (ReviewDTO)request.getAttribute("review");
	String userId = review.getUserId();
	String postDate = review.getPostDate();
	String postText = review.getPostText();
	String nickname = review.getNickname();
	String score = review.getScore();
	String isLike = review.getIsLike();
	String likeNum = review.getLikeNum();
	
	ContentDTO content = (ContentDTO)request.getAttribute("content");
	String contTitle = content.getContTitle();

	
	%>
</script>
</head>
<body>
	작성자 <%=userId %>
	<br>날짜 <%=postDate %>
	<br>contTitle <%=contTitle %>
	<br>내용 <%=postText %>
	<br>별점 <%=score %>
	<br>좋아요수 <%=likeNum %>
	<br>좋아요 누름여부 <%=isLike %>
	
	<br>좋아요, 댓글, 신고버튼
</body>
</html>