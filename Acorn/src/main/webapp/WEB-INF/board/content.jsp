<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Content</title>



<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- 스타일 태그 시작 -->
<style>
.searchInput {
	width: 70vh;
}

/* 댓글 섹션 위에 마진을 추가하는 새로운 CSS 클래스 */
.comment-section {
	margin-top: 10px;
}

/* 네비게이션바 고정 */
.fixed-top {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1030; /* 다른 요소들 위에 표시되도록 z-index 설정 */
}

.navbar-light {
    background-color: #f8f9fa; /* 네비게이션바의 배경색 */
}

/* 검색 버튼 색상 */
.btn-outline-success {
    color: #28a745;
    border-color: #28a745;
}

.btn-outline-success:hover {
    color: white;
    background-color: #28a745;
    border-color: #28a745;
}

/* 글쓰기, 수정, 삭제 버튼 색상 추가 */
.btn-action {
    color: #495057; /* 버튼 텍스트 색상 */
    border-color: #ced4da; /* 버튼 테두리 색상 */
    background-color: #e9ecef; /* 버튼 배경색 */
}

.btn-action:hover {
    color: white;
    background-color: #adb5bd; /* 마우스 오버 시 배경색 */
    border-color: #adb5bd; /* 마우스 오버 시 테두리 색상 */
}

/* 카드 헤더 색상 변경 */
.card-header {
    background-color: #e9ecef; /* 카드 헤더 배경색 */
    color: #495057; /* 카드 헤더 텍스트 색상 */
}

/* 댓글 섹션 스타일 조정 */
.comment-section .card {
    border: 1px solid #ced4da; /* 카드 테두리 */
}

.comment-section .card-header {
    background-color: #f8f9fa; /* 댓글 카드 헤더 배경색 */
    color: #495057; /* 댓글 카드 헤더 텍스트 색상 */
}

/* 컨테이너에 상단 패딩 추가 네비게이션바 글 간격 조정 */
.container {
	padding-top: 100px; /* 네비게이션바 높이에 따라 조정 */
}

/* 수정/삭제/목록 버튼의 간격 조절 */
.btn-spacing {
    margin-right: 2px;
    margin-left: 2px;
}

/* 긴 이미지 게시글 범위 조정 */
.card-body img {
	max-width: 100%;
	height: auto;
}

 /* 게시글 스타일 변경 */
        .post-section {
            padding: 20px 0; /* 상하 패딩 추가 */
            border-bottom: 1px solid #e9ecef; /* 하단 경계선 추가 */
            margin-bottom: 20px; /* 하단 여백 추가 */
        }

        .post-title {
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 10px; /* 제목 아래 여백 추가 */
        }

        .post-meta {
            font-size: 0.9em;
            color: #6c757d;
            margin-bottom: 15px; /* 메타 정보 아래 여백 추가 */
        }

        .post-content {
            font-size: 1.1em;
            line-height: 1.6;
        }

/* 댓글 리스트 스타일 */
.comment-list {
    list-style: none;
    padding: 0;
    margin-top: 10px;
}

.comment-list .comment-item {
    padding: 10px 0;
    border-bottom: 1px solid #e9ecef; /* 구분선 */
}

.comment-item:last-child {
    border-bottom: none; /* 마지막 아이템의 하단 경계선 제거 */
}

.comment-content {
    margin-bottom: 0; /* 댓글 내용의 하단 여백 제거 */
}

.comment-meta {
    font-size: 0.9em;
    color: #6c757d;
}

.comment-actions {
    text-align: right;
}

.comment-actions button {
    font-size: 0.8em;
}

</style>

</head>
<body>
	<!-- 네비게이션바 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<div class="container-fluid">
			<!-- 로고 -->
			<a class="navbar-brand" href="#">로고</a>

			<!-- 토글 버튼 -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- 네비게이션 항목 -->
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mx-auto">
					<!-- 검색 바 -->
					<form class="d-flex w-100">
						<input class="form-control me-2 searchInput" type="search"
							placeholder="검색" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">검색</button>
					</form>
				</ul>
				<ul class="navbar-nav">
					<!-- 로그인, 마이페이지, 회원가입 버튼 -->
					<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-4">
		<!-- 게시글 출력부분 -->
		<div class="post-section">
			<!-- 글 제목 -->
			<div class="post-title">
				<h3>${postTitle}</h3>
			</div>
			<div class="post-meta d-flex justify-content-between">
			<fmt:formatDate value="${postDate}" pattern="yyyy.MM.dd hh:dd:ss" var="formattedDate" />
		    <!-- 왼쪽 부분: 작성자, 작성일 -->
			    <div>
			        <small>
			            작성자: ${nickname} | 
			            작성일: ${formattedDate}
			        </small>
			    </div>
			    
			    <!-- 오른쪽 부분: 조회수, 추천, 댓글 -->
			    <div>
			        <small>
			            조회수: ${viewNum} | 
			            추천: ${likeNum} | 
			            댓글: ${commentCount}
			        </small>
			    </div>
			</div>
			<hr>
			<!-- 글 내용 -->
			<div class="cpost-content">
				${postText}
			</div>
			            <!-- 좋아요 버튼 -->
            <div class="like-button text-center">
                <button type="button" class="btn btn-outline-primary btn-sm">좋아요 <%= request.getAttribute("likeNum") %></button>
            </div>
		</div>

		<!--  게시글과 수정/목록 버튼의 공간 여백을 위한 새로운 클래스 적용 -->
		<div id="comment" class="comment-section">

			<div class="d-flex justify-content-between">
    <!-- 왼쪽에 위치할 목록 버튼 -->
    <div>
        <a href="/Acorn/board/<%=request.getParameter("bn")%>"><button type="button" class="btn btn-action btn-spacing">목록</button></a>
    </div>

    <!-- 오른쪽에 위치할 기타 버튼들 -->
<div>
    <a href="/Acorn/board/write?postId=<%=request.getParameter("postId")%>&bn=<%=request.getParameter("bn")%>"><button type="button" class="btn btn-action btn-spacing">글쓰기</button></a>
    
    <%
    String mismatchError = (String)request.getAttribute("mismatchError");
    System.out.println(mismatchError);
    if(mismatchError==null && session.getAttribute("loginUser")!=null){
    %>
    <a href="/Acorn/board/edit?postId=<%=request.getParameter("postId")%>&bn=<%=request.getParameter("bn")%>"><button type="button" class="btn btn-action btn-spacing">수정</button></a>
    <a href="/Acorn/board/delete?postId=<%=request.getParameter("postId")%>&bn=<%=request.getParameter("bn")%>"><button type="button" class="btn btn-action btn-spacing">삭제</button></a>
	<%}%>
</div>

</div>
		<div style="margin-top: 10px;">
		<jsp:include page="commentMain.jsp"></jsp:include>
		</div>
				<!-- 댓글 내용 -->
			</div>
		</div>
		
</body>
</html>
