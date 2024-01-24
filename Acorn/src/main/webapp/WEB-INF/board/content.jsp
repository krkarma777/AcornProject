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

.btn-custom {
    border: 1px solid #6c757d; /* 테두리 색상과 두께 조정 */
    background-color: white; /* 배경색 지정 */
    color: #6c757d; /* 텍스트 색상 지정 */
}

.btn-custom:hover {
    background-color: #6c757d; /* 마우스 오버 시 배경색 변경 */
    color: white; /* 마우스 오버 시 텍스트 색상 변경 */
}
.btn-del {
    color: #red; /* 텍스트 색상 지정 */
    border: 1px solid #6c757d; /* 테두리 색상과 두께 조정 */
    background-color: white; /* 배경색 지정 */
}

.btn-del:hover {
    background-color: #6c757d; /* 마우스 오버 시 배경색 변경 */
    color: red; /* 마우스 오버 시 텍스트 색상 변경 */
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
		<div class="card">
			<!-- 글 제목 -->
			<div class="card-header">
				<h3>${postTitle}</h3>
				<fmt:formatDate value="${postDate}" pattern="yyyy-MM-dd"
					var="formattedDate" />
				<small>작성자: ${userId} | 작성일: ${formattedDate}</small>
			</div>

			<!-- 글 내용 -->
			<div class="card-body">
				<p class="card-text">${postText}</p>
			</div>
		</div>

		<!--  게시글과 수정/목록 버튼의 공간 여백을 위한 새로운 클래스 적용 -->
		<div id="comment" class="comment-section">

			<div class="d-flex justify-content-between">
    <!-- 왼쪽에 위치할 목록 버튼 -->
    <div>
        <a href="/Acorn/board/<%=request.getParameter("bn")%>"><button type="button" class="btn btn-custom btn-spacing">목록</button></a>
    </div>

    <!-- 오른쪽에 위치할 기타 버튼들 -->
    <div>
        <a href="/Acorn/board/write?postId=<%=request.getParameter("postId")%>&bn=<%=request.getParameter("bn")%>" class="btn btn-custom btn-spacing">글쓰기</button></a>
        <a href="/Acorn/board/edit?postId=<%=request.getParameter("postId")%>&bn=<%=request.getParameter("bn")%>"><button type="button" class="btn btn-custom btn-spacing">수정</button></a>
        <a href="/Acorn/board/delete?postId=<%=request.getParameter("postId")%>&bn=<%=request.getParameter("bn")%>"><button type="button" class="btn btn-del btn-spacing">삭제</button></a>
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
