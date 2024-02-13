<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
    nav {
        background-color: gray;
    }

</style>

<nav class="navbar navbar-expand" style="background-color:transparent;">
        <div class="container-fluid">
            <a class="navbar-brand" href="<%= request.getContextPath()%>/main">메인</a>
            <a class="navbar-brand" href="<%=request.getContextPath()%>/memberListServlet">회원리스트(디버그)</a>
        </div>
</nav>