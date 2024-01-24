<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입에 실패할 경우, 나타나는 페이지.jsp -->

<head>
    <meta charset="UTF-8">
    <title>회원가입 실패</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            height: 100vh;
        }

        #errorMessage {
            background-color: #ff6347;
            color: white;
            padding: 20px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 20px;
        }

        #sitesShortCut {
            text-align: center;
        }

        #sitesShortCut a {
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            margin: 0 5px;
        }
    </style>
    
</head>

<body>

	<!-- 회원가입에 왜 실패했는지 알려주는 메세지를 신규 회원정보 검증&등록 창에서 가져옴 -->
	<% String errorMesg = (String)request.getAttribute("mesg"); %>

    <div id="errorMessage">
        회원가입 실패<br>
		<!-- 회원가입에 왜 실패했는지 알려주는 메세지 출력 -->
       	<%= errorMesg %>
    </div>

    <div id="sitesShortCut">
        <a href="<%=request.getContextPath()%>/Login">로그인</a> | <a href="<%=request.getContextPath()%>/RegisterTerms">회원가입</a>
    </div>
</body>
</html>
