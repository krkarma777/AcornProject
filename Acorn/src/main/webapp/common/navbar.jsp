<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<nav class="navbar navbar-expand" style="background-color:transparent;">
        <div class="container-fluid">
            <a class="navbar-brand" href="<%= request.getContextPath()%>/main">문밤</a>
            <form class="d-flex" role="search">
                <input class="form-control me-1" type="search" placeholder="영화 정보 입력" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <ul class="navbar-nav">
			<% 
				MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
				if(dto != null){	//로그인 상태
			%>
                <li class="nav-item"><a href="<%= request.getContextPath()%>/Logout" class="nav-link">로그아웃</a></li>
				<li class="nav-item"><a href="<%= request.getContextPath()%>/AdminMainServlet" class="nav-link">마이페이지</a></li>		
			<%
				} else {			//로그인 아닌 상태
			%>
				<li class="nav-item"><a href="<%= request.getContextPath()%>/Login" class="nav-link">로그인</a></li>
				<!-- <li class="nav-item"><a href="#" class="nav-link">회원가입</a></li> -->
			<%
				}
			%>                
            </ul>
        </div>
</nav>

<!-- <nav class="" style="background-color:transparent; position:fixed; top:0; left:0; height: 50px; width:100%">
	<div class="d-flex container-fluid px-0">
		<div class="position-relative top-0 start-0"><a href="#">문밤</a></div>
		<form class="d-flex " role="search">
        	<input class="form-control me-1" type="search" placeholder="영화 정보 입력" aria-label="Search">
    		<button class="btn btn-outline-success" type="submit">Search</button>
    	</form>
    	<div class="position-fixed top-0 end-0"><a href="LoginServlet">로그인</a></div>
    </div>
</nav> -->