<%@page import="com.dto.ContentDTO"%>
<%@page import="com.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
	#body{
		padding-left: 10%;
		padding-right: 10%;
	}
	#postDate{
		font-size: 70%;
		color: gray;
	}
	#contImg{
		text-align: right;
	}
	.like_btn{
		-webkit-user-select:none;
		-moz-user-select:none;
		-ms-user-select:none;
		user-select:none
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	<%
	ReviewDTO review = (ReviewDTO)request.getAttribute("review");
	Long postId = review.getPostId(); 
	String userId = review.getUserId();
	String postDate = review.getPostDate();
	String postText = review.getPostText();
	String nickname = review.getNickname();
	String score = review.getScore();
	String isLike = review.getIsLike();
	String likeNum = review.getLikeNum();
	
	ContentDTO content = (ContentDTO)request.getAttribute("content");
	String contTitle = content.getContTitle();
	String contImg = content.getContImg();

	
	%>
	$(document).ready(function(){
		$(".like_btn").on("click", likeToggle)
	});
	
	// 공감버튼 토글
	function likeToggle(){

		//버튼에 적혀있는 하트 공백제거해서 가져오기
		var statement = $(this).text().trim();
		var isLike = 0;
		
		// 공백하트인지 꽉찬 하트인지 검사해서 반대로 바꾸기
		if(statement == "♥"){
			$(this).text("♡");
			isLike = 0;
		} else if(statement == "♡"){
			$(this).text("♥");
			isLike = 1;
		}
		
		//로그인정보가 있을 때
		//DB에 비동기 반영
		<%if(userId!=null){%>
		
			$.ajax(
				{
					type: "post",
					url:"UpdateLikeServlet",
					data: {
						"userId": "<%=userId%>",
						"postId": <%=postId%>,
						"isLike": isLike
					},
					dataType: "text",
					success: function(data, status, xhr){
						if(isLike==0)
							$("#likeNum").text($("#likeNum").text()-1);
						else{
							$("#likeNum").text($("#likeNum").text()-1+2);
						}
					},
					error: function(xhr, status, e){
					}
				}//json	
			);//ajax
		<%}%>//if
	}
</script>
</head>
<body>
	<jsp:include page="//common/navbar.jsp"></jsp:include>
	<div class="row" id="body">
		<div class="row" id="top">
			<div class="col-lg-9">
				<div class="row"><%=userId %> <span id="postDate"><%=postDate %></span></div>
				<div class="row"><%=contTitle %><span id="score">☆ <%=Double.parseDouble(score)/2 %></span></div>
			</div>

			<div class="col-lg-3" id="contImg"><img src="<%=contImg %>" width="100" height="100"></div>
			<hr>
		</div>
		<div class="row" id="middle">
			<%=postText %>
			<span>
				<span class="like_btn" style="color:red">
				<%if("1".equals(isLike)){%>♥ 
				<%}else{ %>♡<%} %>
				</span>
				
				<span id="likeNum"><%=(likeNum!=null)?(likeNum):("") %></span>
			</span>
			
			
			<hr>
		</div>
		<div class="row" id="bottom"></div>
		
		
		<br>좋아요, 댓글, 신고버튼
	</div>
	
	
</body>
</html>