<%@page import="com.dto.RateDTO"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
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

	@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
    .rate { display: inline-block;border: 0;margin-right: 15px;}
	.rate > input {display: none;}
	.rate > label {float: right;color: #ddd}
	.rate > label:before {display: inline-block;font-size: 1rem;padding: .3rem .2rem;margin: 0;cursor: pointer;font-family: FontAwesome;content: "\f005 ";}
	.rate .half:before {content: "\f089 "; position: absolute;padding-right: 0;}
	.rate input:checked ~ label, 
	.rate label:hover,.rate label:hover ~ label { color: #f73c32 !important;  } 
	.rate input:checked + .rate label:hover,
	.rate input input:checked ~ label:hover,
	.rate input:checked ~ .rate label:hover ~ label,  
	.rate label:hover ~ input:checked ~ label { color: #f73c32 !important;  } 
	
	.pad_side{
		padding-left: 10%;
		padding-right: 10%;
	}
	#cont_view{
		padding-top: 30px;
		padding-bottom: 60px;
		background-color: #f8f8f8;
	}
	#cont_img{
		min-width: 300px;
		
	}
	#cont_title{
		padding-top: 20px;
		padding-bottom: 20px;
	}
	
	#postText{
		border: none;
	   /*  overflow: scroll; */
	   overflow:auto;
	   overflow-x:hidden;
	    outline: none;
	
	    -webkit-box-shadow: none;
	    -moz-box-shadow: none;
	    box-shadow: none;
	
	    resize: none; /*remove the resize handle on the bottom right*/
	}
	
	#con_desc{
		padding-bottom: 20px;
		white-space: pre-wrap;
	}
	
	#cont_myreview_container{
		display: none;
		padding-top: 30px;
	}
	
	#myreview_text{
		max-height: 300px;
		overflow: hidden;
		font-size: 16px;
	}
	
	#myreview_footer{
		font-size: 16px;
	}
	
	#myreview_link{
		text-decoration: none;
		color: white;
	}

	#review_col{
		margin: 10px;
		background-color: #f8f8f8;
		border: 1px solid black;
		border-radius: 16px;
		padding: 20px;
		max-width: 25%;
	}
	#review_title{
		margin-top: 50px;
		margin-bottom: 25px;
		text-decoration-line: none;
		color = black;
	}
	#review_nick{
		font_size: 18px;
		height: 24px
	}
	#review_hr{
		margin-bottom: 8px;
	}
	#remove_deco{
		text-decoration: none;
		color: black;
	}
	#review_body{
		font-size: 15px;
		height: 135px;
		margin-bottom: 5px;
		
    	overflow-wrap: break-word;
    	overflow: hidden;
	}
	#review_score{
		font-size: 17px;
	}
	
	.like_btn{
		-webkit-user-select:none;
		-moz-user-select:none;
		-ms-user-select:none;
		user-select:none
	}
	#show_length{
		color: gray;
		text-align: right;
	}
	#graph{
		height: 70px;
		padding-left: 5px;
		padding-right: 5px;
		margin-bottom: 20px
		
	}
	.graph_bar{
		/* background-color: #0d6efd; */
		background-color: #0982e6;
		margin-left: 1px;
		margin-right: 1px;
		border-top-left-radius: 8px;
		border-top-right-radius: 8px;
	}
	
	#reviews_title{
		text-decoration-line: none;
		color: black;
	}
	
	
</style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

<%
	
	
	ContentDTO content = (ContentDTO)request.getAttribute("content");
	
	//예외처리: DB에 contid에 해당하는 데이터가 없을 경우 -> 이전화면으로
	Long contId = null;
	String contTitle = null;
	String description = null;
	String contImg = null;
	if(content==null){ // 컨텐츠정보가 null일 경우
		%>
		location.href = "MoveToContentsHomeServlet"; // 이전 화면으로
		<%
	} else{
		contId = content.getContId();
		contTitle = content.getContTitle();
		description = content.getDescription();
		contImg = content.getContImg();
	}
	
	
	MemberDTO login = (MemberDTO)session.getAttribute("loginUser");
	String userId = null;
	String nickname = null;
	if(login!=null){
		userId = login.getUserId();
		nickname = login.getNickname();
	}
	
	List<ReviewDTO> reviewList = (List<ReviewDTO>)request.getAttribute("reviewList");
	
	//avgRate 구하기, 별점범위당 갯수 구하기
	List<RateDTO> rateList = (List<RateDTO>)request.getAttribute("rateList");
	int rateAmount = 0;
	if(rateList!=null){ //0이 아닐 경우
		rateAmount = rateList.size();
	}
	//모든 별점 순회
	double sum = 0;
	double[] rateDistribution = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	
	for(int i=0;i<rateAmount;i++){
		RateDTO rate = rateList.get(i);
		double score = rate.getScore();
		sum += score;
		//별점(score)가 10이면 [9]에 +1증가
		rateDistribution[(int)score-1]++;
	}
	
	String avgRate = "리뷰가 존재하지 않습니다.";
	// 별점이 1개 이상일 때
	if(rateAmount>0){
		avgRate = String.format("%.1f", sum/rateAmount/2);
	}	
%>
	//최대글자수
	var max_length = 200;

	//ready
	$(document).ready(function(){
		$("#show_length").text($("#postText").val().length+"/"+max_length); //글자수 표시
		$("#writeReview").on("click", writeReview);  //리뷰작성
		$("#postText").on("keyup", check_length); 	 //글자수 제한
		$("#postText").on("keypress", check_enter);  //엔터키 제한
		$(".rate input").on("change", rating)  		//별점 선택
		$(".like_btn").on("click", likeToggle) 		// 공감버튼 클릭
		
		//화면 로딩시 내 리뷰 표시 함수 
		setMyReview();
		
		// 별점 막대그래프 높이 설정 함수
		setAvgGraph();
		
	});//ready
	
	
	// 별점 막대그래프 높이 설정 함수
	function setAvgGraph(){
		<%
		for(int i=0;i<rateDistribution.length;i++){
			// 버전1
			//전체높이 * 해당별점갯수/전체별점갯수
			//문제: y축이 너무 높아짐
			//double height = 70*rateDistribution[i]/rateAmount;
			
			// 버전2
			//y축 수치값을 (가장 많은 갯수 + 10%)로 설정
			//전체높이 * 해당별점갯수/(가장 높은 갯수 + 10%)
			double max = rateDistribution[0];
			for(double num: rateDistribution){ 
				if(num>max){ //최대값 비교해서 구하기
					max = num;
				}
			}
			// 70px * (최대값+10%)
			double height = 70*rateDistribution[i]/(max*1.1);
			
			if (height> 70)
				height = 70;
			if (height == 0)
				height = 2;
			%>
			$("#score<%=i+1%>").css("height", "<%=height%>px");
		<%}%>
	}
	
	//화면 로딩시 내 리뷰 표시 함수 
	function setMyReview(){
		<%
		//로그인 정보 확인
		if(login!=null){
		%>
		// 화면 최초 생성시 로그인상태+작성했던 리뷰가 있다면 불러와서 표시
		//내 리뷰 불러오기
		$.ajax(
			{
				type: "post",
				url:"SelectReviewByUserServlet",
				data: {
					"userId": "<%=userId%>",
					"contId": "<%=contId%>"
				},
				dataType: "text",
				success: function(data, status, xhr){
					//console.log("성공: " + data);
					if(data.length>0){
						var jsonData = JSON.parse(data);
						$("#postText").text(jsonData.postText);
						updateMyReview(jsonData)
						$('#exampleModal').modal('hide');
						$("#cont_myreview_container").show();
						//console.log("별점: " + jsonData.score)
						if(jsonData.score!=null){
							$("#rating"+jsonData.score).attr("checked", "checked");
						}	
					}
				},
				error: function(xhr, status, e){
					console.log("실패: " + xhr.status);
				}
			}//ajax	
		);//ajax
		<%}%>//if 종료
	}
	
	// 공감버튼 토글
	function likeToggle(){

		// 버튼 누른 리뷰의 postId 가져오기
		var postId = $(this).attr("data-postId");
		
		//버튼에 적혀있는 하트 공백제거해서 가져오기
		var statement = $(this).text().trim();
		var isLike = 0;
		//console.log(statement)
		
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
						"postId": postId,
						"isLike": isLike
					},
					dataType: "text",
					success: function(data, status, xhr){
						if(isLike==0)
							$("#likeNum"+postId).text($("#likeNum"+postId).text()-1);
						else{
							$("#likeNum"+postId).text($("#likeNum"+postId).text()-1+2);
						}
					},
					error: function(xhr, status, e){
					}
				}//json	
			);//ajax
		<%}%>//if
	}
	
	// 별점 선택
	function rating(){
		$.ajax(
			{
				type: "post",
				url:"UpdateScoreServlet",
				data: {
					"userId": "<%=userId%>",
					"contId": "<%=contId%>",
					"score": this.value
				},
				dataType: "text",
				success: function(data, status, xhr){
					//console.log("성공: " + data);
				},
				error: function(xhr, status, e){
					//console.log("실패: " + xhr.status);
				}
			}		
		);
	}
	// 글자수 제한
	function check_length(){
		//console.log(this.value.length);
		var length = this.value.length;
		if(length>max_length){
			this.value = this.value.substr(0, max_length);
			this.focus();
		}
		$("#show_length").text(length+"/"+max_length);
	}
	// 엔터키 제한
	function check_enter(){
		if(event.keyCode==13){
			event.returnValue=false;
		}
	}
	
	// 내 리뷰란 업데이트 함수
	function updateMyReview(review){
		$("#myreview_user").text("<%=nickname %>");
		if(review.postText.length>150){
			$("#myreview_text").text(review.postText.substr(0, 145)+" ...");
		}else{
			$("#myreview_text").text(review.postText);
		}
		var length = $("#postText").val().length;
		
		// 모달창 글자수 처음 세팅
		$("#show_length").text(length+"/"+max_length);
	}
	
	// 리뷰 작성 완료
	function writeReview(){

		//로그인 정보 확인
		<%
		if(login!=null){
		%>
		
		var contId = $("#contId").val();
		var postText = $("#postText").val().substr(0, max_length);
		//내용이 있을 시만 저장작업 진행 && contId에 null값이 저장되지 않았을 경우에만 진행
		if(postText.length!=0 && contId !="null"){
			$.ajax(
				{
					type: "post",
					url:"WriteReviewServlet",
					data: {
						"contId": contId,
						"userId": "<%=userId%>",
						"nickname": "<%=nickname%>",
						"postText": postText
					},
					dataType: "text",
					success: function(data, status, xhr){
						//console.log("성공: " + data);
						var jsonData = JSON.parse(data);  //text->json
						updateMyReview(jsonData);         //리뷰 표시 업데이트 함수
						$('#exampleModal').modal('hide');
						$("#cont_myreview_container").show();
						
					},
					error: function(xhr, status, e){
						//console.log("실패: " + xhr.status);
					}
				}//json
			);//ajax
		}//내용검사if
		<%}%>
	}//function
</script>
</head>
<body>
	<!--네비게이션바  -->
	<%-- <jsp:include page="//common/navbar.jsp"></jsp:include> --%>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Navbar</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Link</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Dropdown
	          </a>
	          <ul class="dropdown-menu">
	            <li><a class="dropdown-item" href="#">Action</a></li>
	            <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li>
	          </ul>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link disabled" aria-disabled="true">Disabled</a>
	        </li>
	      </ul>
	      <form class="d-flex" role="search">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
	
	<!--헤더  -->
	<div class="row" id="header"></div>
	
	<!--바디  -->
	<div class="row" id="body">
		<!--컨텐츠 표시  -->
		<div class="row pad_side" id="cont_view">
		
			<div class="col-lg-3" id="cont_img">
				<div class="card" style="width: 18rem;">
				  <img src="<%=contImg %>" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">대중의 평가</h5>
				    <h5 class="card-text" id="avgRate">☆<%=avgRate %> (<%=rateAmount %>)</h5><br>
				    별점 분포
				    <div class="row align-items-end" id="graph">
				    	<div class="col graph_bar graph-bar" id="score1"></div>
				    	<div class="col graph_bar graph-bar" id="score2"></div>
				    	<div class="col graph_bar graph-bar" id="score3"></div>
				    	<div class="col graph_bar graph-bar" id="score4"></div>
				    	<div class="col graph_bar graph-bar" id="score5"></div>
				    	<div class="col graph_bar graph-bar" id="score6"></div>
				    	<div class="col graph_bar graph-bar" id="score7"></div>
				    	<div class="col graph_bar graph-bar" id="score8"></div>
				    	<div class="col graph_bar graph-bar" id="score9"></div>
				    	<div class="col graph_bar graph-bar" id="score10"></div>
				    </div>
				    
				    <!-- Button trigger modal -->
				    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Write Your Feeling !</button>
				  </div>
				</div>
			</div>
			
			<div class="col-lg-9" id="cont_info">
				<div class="row" id="cont_title">
					<h2><%=contTitle %></h2>
				</div>
				<div class="row" id="con_desc"><%=description %>
				</div>
				<hr>
				<div class="row">
					
				</div>
				<div class="row" id="cont_myreview_container">
					<div class="col-lg-6" id="cont_myreview">
						<a href="" id="myreview_link">
							<div class="card">
							  <div class="card-header" id="myreview_user">
							    Quote
							  </div>
							  <div class="card-body">
							    <blockquote class="blockquote mb-0">
							      <p id="myreview_text">A well-known quote, contained in a blockquote element.</p>
							      <footer class="blockquote-footer" id="myreview_footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
							    </blockquote>
							  </div>
							</div>
						</a>
					</div>
					
					<div class="col-lg-4">
						<fieldset class="rate">
                             <input type="radio" id="rating10" name="rating" value="10"><label for="rating10" title="5점"></label>
                             <input type="radio" id="rating9" name="rating" value="9"><label class="half" for="rating9" title="4.5점"></label>
                             <input type="radio" id="rating8" name="rating" value="8"><label for="rating8" title="4점"></label>
                             <input type="radio" id="rating7" name="rating" value="7"><label class="half" for="rating7" title="3.5점"></label>
                             <input type="radio" id="rating6" name="rating" value="6"><label for="rating6" title="3점"></label>
                             <input type="radio" id="rating5" name="rating" value="5"><label class="half" for="rating5" title="2.5점"></label>
                             <input type="radio" id="rating4" name="rating" value="4"><label for="rating4" title="2점"></label>
                             <input type="radio" id="rating3" name="rating" value="3"><label class="half" for="rating3" title="1.5점"></label>
                             <input type="radio" id="rating2" name="rating" value="2"><label for="rating2" title="1점"></label>
                             <input type="radio" id="rating1" name="rating" value="1"><label class="half" for="rating1" title="0.5점"></label>

                         </fieldset>
					</div>	
					<div class="col-lg-2"></div>
				</div>
				
			</div>
		</div>
		<!--감상평들 표시  -->
		<!--감상평 리스트 데이터 전달받아야함 (reviewList) -->
		<div class="row pad_side" id="review_title">
			<a href="" id="reviews_title"><h2>Reviews ></h2></a>
		</div>
		<div class="row pad_side" id="review_row">
			<%for(int i=1; i<=2;i++){%>
			<div class="row">
				<%for(int j=1; j<=4;j++){%>
				<div class="col" id="review_col">
					<%
					//System.out.println(reviewList.size());
					int index = (i-1)*4+(j-1);
					if(reviewList.size()-1>=index){
						String score = reviewList.get(index).getScore();
						if(score==null){
							score="0";
						}
						%>

						<div id="review_nick">
							<%=reviewList.get(index).getNickname() %>
						</div>
						<hr>
						<a id="remove_deco" href="ShowReviewServlet?postId=<%=reviewList.get(index).getPostId()%>">
							<div id="review_body">
								<%=reviewList.get(index).getPostText() %>
							</div>
						</a>
						<div id="review_score">
							<span>☆ <%=Double.parseDouble(score)/2 %></span>
							<span class="like_btn" style="color:red" data-postId="<%=reviewList.get(index).getPostId() %>">
							<%if("1".equals(reviewList.get(index).getIsLike())){
								//System.out.println("♥"+reviewList.get(index).getIsLike());
							%>♥ 
							<%}else{ 
								//System.out.println("♡"+reviewList.get(index).getIsLike());
							%>♡
							<%} %>
							
							</span>
							<span id="likeNum<%=reviewList.get(index).getPostId() %>"><%=(reviewList.get(index).getLikeNum()!=null)?(reviewList.get(index).getLikeNum()):("") %></span>
						</div>
					<%}else{%>
						<div id="review_nick"></div>
						<hr>
						<div id="review_body"></div>
						<div id="review_score"></div>
					<%}
					 %>
				</div>
				<%}%>
			</div>
			<%}%>
		</div>
	</div>
	
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	
	<!--푸터  -->
	<div class="container">
		  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
		    <div class="col-md-4 d-flex align-items-center">
		      <a href="/" class="mb-3 me-2 mb-md-0 text-body-secondary text-decoration-none lh-1">
		        <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
		      </a>
		      <span class="mb-3 mb-md-0 text-body-secondary">&copy; 2023 Company, Inc</span>
		    </div>
		
		    <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
		      <li class="ms-3"><a class="text-body-secondary" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#twitter"/></svg></a></li>
		      <li class="ms-3"><a class="text-body-secondary" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#instagram"/></svg></a></li>
		      <li class="ms-3"><a class="text-body-secondary" href="#"><svg class="bi" width="24" height="24"><use xlink:href="#facebook"/></svg></a></li>
		    </ul>
		  </footer>
	</div>
	
	<!-- 모달창 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">간편 리뷰 작성</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	<input type="hidden" value="<%=contId %>" id="contId">
	        <textarea cols="50" rows="12" id="postText"></textarea>
	        <p id="show_length">length</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <!-- <button type="button" class="btn btn-primary" id="writeReview">Save changes</button> -->
	        <button type="button" class="btn btn-primary" id="writeReview" data-bs-toggle="popover" data-bs-title="알림" data-bs-content="로그인이 필요한 작업입니다">Save changes</button>
	      </div>
	    </div>
	  </div>
</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script>
	const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
	const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))
</script>
</body>
</html>