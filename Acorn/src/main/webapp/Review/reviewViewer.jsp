<%@page import="com.dto.MemberDTO"%>
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
	#btns{
		
		margin-top: 10px;
		margin-bottom: 5px;
	}
	#like_wrapper2{

	}
	#like_wrapper{
		width: 100px;
		border: none;
		border-radius: 8px;
		padding-left: 0px;
		padding-right: 0px;
	}
	#show_more{
		border: none;
		border-radius: 8px;
		font-size: 20px;
		background-color: inherit;
	}
	
	#show_more_wrapper{
		text-align: right;
	}

	.dropdown-item:focus{
		/* background: gray; */
	}
	.noEffect{
		text-decoration: none;
		color: black;
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
	Long contId = review.getContId();
	
	ContentDTO content = (ContentDTO)request.getAttribute("content");
	String contTitle = content.getContTitle();
	String contImg = content.getContImg();

	String mesg = (String)request.getAttribute("mesg");
	%>
	console.log("<%=mesg%>");
	if("<%=mesg%>"!="null"){
		alert("<%=mesg%>");
	}
	$(document).ready(function(){
		$("#like_wrapper").on("click", likeToggle)
	});
	
	
	// 공감버튼 토글
	function likeToggle(){

		//버튼에 적혀있는 하트 공백제거해서 가져오기
		var statement = $(".like_btn").text().trim();
		var isLike = 0;
		
		// 공백하트인지 꽉찬 하트인지 검사해서 반대로 바꾸기
		if(statement == "♥"){
			$(".like_btn").text("♡");
			isLike = 0;
		} else if(statement == "♡"){
			$(".like_btn").text("♥");
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
			<div class="col-9">
				<div class="row"><%=userId %> <span id="postDate"><%=postDate %></span></div>
				<div class="row"><a href="ShowContentServlet?contId=<%=contId%>" class="noEffect"><%=contTitle %></a><span id="score">☆ <%=Double.parseDouble(score)/2 %></span></div>
			</div>

			<div class="col-3" id="contImg"><a href="ShowContentServlet?contId=<%=contId%>"><img src="<%=contImg %>" width="100" height="100"></a></div>
			<hr>
		</div>
		<div class="row" id="middle">
			<!-- 리뷰 내용  -->
			<%=postText %>
			
			<!-- 버튼 그룹 -->
			<div id="btns" class="row">
				<!-- 좋아요 버튼 -->
				<div class="col" id="like_wrapper2">
					<button id="like_wrapper">
						<span class="like_btn" style="color:red">
						<%if("1".equals(isLike)){%>♥ 
						<%}else{ %>♡<%} %>
						</span>
						
						<span id="likeNum"><%=(likeNum!=null)?(likeNum):("") %></span>
					</button>
				</div>
				<!-- 더보기 버튼  -->
				<div class="col" id="show_more_wrapper">
					<!-- <button id="show_more">
						...
					</button> -->
					<div class="btn-group dropup">
					  <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					    ...
					  </button>
					  <ul class="dropdown-menu">
					    <li><a href="#" class="dropdown-item noEffect">공유</a></li>
					    <li><a href="ReportServlet?postId=<%=postId %>&reason=부적절한리뷰" class="dropdown-item noEffect">리뷰신고</a></li>
					    <li><a href="#" class="dropdown-item noEffect">AI</a></li>
					  </ul>
					</div>
				</div>
			</div>
			
			
			
			<hr>
		</div>
		<div class="row" id="bottom"></div>
		
		
		<jsp:include page="../WEB-INF/board/commentMain.jsp"></jsp:include>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>