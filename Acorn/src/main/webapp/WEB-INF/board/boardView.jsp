<%@page import="com.dto.PostDTO"%>
<%@page import="com.dto.PageDTO"%>
<%@ page import="java.sql.*, java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<%
            PageDTO<PostDTO> pDTO = (PageDTO<PostDTO>) request.getAttribute("pDTO");
            List<PostDTO> list = pDTO.getList();
            
         	String postBoard = (String) request.getAttribute("postBoard");
        %>

<title><%= postBoard %> Board</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>

.list-group{
max-width:1000px;
margin:auto;
}

/* 페이징 글자색 검정으로 변경 */
.font-black{
color:black;
}

/* 선택된 페이징 번호 빨강으로 강조 */
.font-red{
color:red;
}

/* 페이징 밑줄 제거 */
.no-underline {
	text-decoration: none;
}

/* 컨테이너에 상단 패딩 추가 네비게이션바 글 간격 조정 */
.container {
	padding-top: 100px; /* 네비게이션바 높이에 따라 조정 */
}

/* 검색창 크기 조절 */
.searchInput {
	width: 70vh;
}

/* 게시글 목록 항목 높이 조절 */
.list-group-item {
	padding: 5px; /* 게시글 목록 높이를 조절하기 위한 padding 값 조절. */
	font-size: 12px; /* 필요에 따라 폰트 크기를 조절할 수도 있습니다. */
}

/* 버튼 크기 조절 */
.custom-btn {
	padding: 4px 8px; /* 버튼의 내부 여백 조절 */
	font-size: 12px; /* 버튼 내 텍스트의 폰트 크기 조절 */
	/* height: 40px; 추가적으로 높이를 조절하고 싶은 경우 */
	/* width: 100px; 추가적으로 너비를 조절하고 싶은 경우 */
}

/* 게시글 목록과 버튼 사이의 간격 조절 */
.margin-top {
	margin-top: 10px; /* 위쪽 여백 20px 추가 */
}
/* 네비게이션바 고정 */
.fixed-top {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1030; /* 다른 요소들 위에 표시되도록 z-index 설정 */
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
		



		<!-- 게시글 목록 -->
		<div class="list-group">
		<h2><%= postBoard %> Board</h2>
		
			<% for(PostDTO post : list) {%>
			<a href="/Acorn/board/content?postId=<%= post.getPostId() %>&bn=<%= postBoard %>"
				class="list-group-item list-group-item-action">
				<div class="row">
					<div class="col-md-6"><%= post.getPostTitle() %></div>
					<div class="col-md-3">
						작성자:
						<%= post.getUserId() %></div>
					<fmt:formatDate value="<%= post.getPostDate() %>"
						pattern="yyyy-MM-dd" var="formattedDate" />
					<div class="col-md-3">작성일: ${formattedDate}</div>
				</div>

			</a>
			<% } %>
			
					<!-- 버튼 그룹에 간격을 추가하기 위한 클래스 적용 -->
		<div class="mb-3 d-flex justify-content-end margin-top">
			<!-- Preview and submit button group -->
			<div>
				<a href="/Acorn/board/write?bn=<%= postBoard %>"><button type="button"
						class="btn btn-primary custom-btn">글쓰기</button></a>
			</div>
		</div>
		</div>


		<!-- 페이징 로직 -->
		<div class="page-numbers text-center ">
			<!-- text-center 클래스를 추가하여 가운데 정렬 -->
			<%
    int curPage = pDTO.getCurPage();
    int perPage = pDTO.getPerPage();
    int totalCount = pDTO.getTotalCount();
    int totalPage = (int) Math.ceil((double) totalCount / perPage);
    
    for (int i = 1; i <= totalPage; i++) {
        if (i == curPage) {
            // 현재 페이지는 링크를 걸지 않고 강조 스타일을 적용합니다.
            out.print("<span class='current-page font-red'>" + i + "</span>");
        } else {
            // 다른 페이지는 해당 페이지로 이동할 수 있는 링크를 생성합니다.
            out.print("<a class='no-underline font-black' href='/Acorn/board/movie?curPage=" + i + "'>" + i + "</a>");
        }
        if (i < totalPage) {
            // 페이지 사이에 구분자(예: |)를 넣을 수 있습니다.
            out.print(" | ");
        }
    }
    %>
		</div>



		<!-- 기타 추가 내용 -->
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
