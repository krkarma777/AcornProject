<%@page import="com.domain.dto.PostDTO"%>
<%@page import="com.domain.dto.PageDTO"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<style type="text/css">

/* 폰트 적용한 부분 */
@font-face {
    font-family: 'Pretendard-Regular'; 
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
body{
    font-family: 'Pretendard-Regular';

}
/* 웹 페이지의 전체 body 요소에 대한 스타일 정의 */
body{

 display: flex;
            align-items: center; /* 수직 축에서 가운데 정렬 */
            justify-content: center;  /* 수평 축에서 가운데 정렬 */
            height: 100vh; 
            margin: 0;
            flex-direction: column;
}

        
.page-numbers {
    display: flex;  /* 페이지 링크를 수평으로 정렬 */
}

.page-link {
    margin-right: 5px;  /* 페이지 링크 숫자 사이의 여백 조절 */
    text-decoration: none; /* 밑줄 제거 */
    color: black; /* 글자 색상 검정색으로 지정 */
}

.current-page {
    font-weight: bold;  /* 선택된 페이지의 번호 굵게 표시 */
    color: red; /* 현재 페이지 글자 색상 빨간색으로 지정 */
}      
 
 /* 테이블 스타일 조정 */
 table {
    width: 80%; /* 테이블의 너비를 조절하세요 */
    margin: 20px auto; /* 가운데 정렬을 위해 auto를 사용 */
    border-collapse: collapse;
}

th, td {
    padding: 8px;
    text-align: left;
}

b{
 color: red; /* MOVIEBOARD 색 변경 */
 font-size: 30px; /* 글자 크기 키우기 */

}



</style>
<title>게시판 목록</title>
</head>
 

        
      

<body>
    <b>MOVIE BOARD</b>

    <%
        PageDTO<PostDTO> pDTO = (PageDTO<PostDTO>) request.getAttribute("pDTO");
        List<PostDTO> list = pDTO.getList();
        String searchName = (String) request.getAttribute("searchName");
        String searchValue = (String) request.getAttribute("searchValue");
    %>

    <!-- 게시글 목록 표시 -->
    <table>
        <tr>
            <th>글 번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>작성일</th>
        </tr>
        <% for(PostDTO post : list) { %>
            <tr>
                <td><%= post.getPostId() %></td>
                <td><%= post.getUserId() %></td>
                <td><a href="/Board/movie/content?postId=<%= post.getPostId() %>"><%= post.getPostTitle() %></a></td>
                <td><%= post.getPostDate() %></td>
            </tr>
        <% } %>
    </table>

    <!-- 페이징 처리 -->
    <div class="page-numbers">
        <%
            int curPage = pDTO.getCurPage();
            int perPage = pDTO.getPerPage();
            int totalCount = pDTO.getTotalCount();
            int totalPage = (int) Math.ceil((double) totalCount / perPage);

            for(int i = 1; i <= totalPage; i++) {
                if(i == curPage) {
                    out.print("<span class='current-page'>" + i + "</span>");
                } else {
                    out.print("<a href='/Acorn/board/movie?curPage=" + i + "&searchName=" + searchName + "&searchValue=" + searchValue + "' class='page-link'>" + i + "</a>");
                }
            }
        %>
    </div>

    <!-- 검색 폼 -->
    <form action="Board" method="get">
        <input type="hidden" name="curPage" value="1">
        <input type="text" name="searchValue" placeholder="검색어를 입력하세요">
        <button type="submit">검색</button>
    </form>

</body>

</html>