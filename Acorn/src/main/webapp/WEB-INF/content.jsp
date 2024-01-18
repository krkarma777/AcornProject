<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Content</title>
    
    

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha384-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    
    <!-- 스타일 태그 시작 -->
    <style>
    	.searchInput{
    	width :70vh;
    	}
    	
     /* 댓글 섹션 위에 마진을 추가하는 새로운 CSS 클래스 */
       .comment-section {
       margin-top: 30px; 
        }
        
    
    
    
        
    </style>
    
</head>
<body>
    <!-- 네비게이션바 -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <!-- 로고 -->
            <a class="navbar-brand" href="#">로고</a>

            <!-- 토글 버튼 -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- 네비게이션 항목 -->
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mx-auto">
                    <!-- 검색 바 -->
                    <form class="d-flex w-100" >
                        <input class="form-control me-2 searchInput" type="search" placeholder="검색" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">검색</button>
                    </form>
                </ul>
                <ul class="navbar-nav">
                    <!-- 로그인, 마이페이지, 회원가입 버튼 -->
                    <li class="nav-item">
                        <a class="nav-link" href="#">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">마이페이지</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">회원가입</a>
                    </li>
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
                <fmt:formatDate value="${postDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                <small>작성자: ${userId} | 작성일: ${formattedDate}</small>
            </div>

            <!-- 글 내용 -->
            <div class="card-body">
                <p class="card-text">${postText}</p>
            </div>
        </div>

        <!-- 댓글 섹션 (추가 구현 필요) 게시글과 댓글 공간 여백을 위한 새로운 클래스 적용 -->
        <div id="comment" class="comment-section">
        
         <!-- Text input area -->
            <div class="mb-3">
                <textarea class="form-control" rows="4" placeholder="내용을 입력해주세요."></textarea>
            </div>

            <!-- preview button group -->
            <!-- 버튼을 오른쪽 끝으로 정렬하기 위한 'justify-content-end' 클래스 적용 -->
            <div class="mb-3 d-flex justify-content-end">
               
                <!-- Preview and submit button group -->
                <div>
                    <a href="/Acorn/board/movie"><button type="button" class="btn btn-primary">목록</button></a>
                    <button type="submit" class="btn btn-primary">등록</button>
                </div>
            </div>
            <!-- 댓글 내용 -->
        </div>
    </div>
</body>
</html>
