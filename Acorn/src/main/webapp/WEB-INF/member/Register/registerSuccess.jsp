<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입에 성공할 경우, 나타나는 페이지의 jsp -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/register_result.css">

<head>
    <meta charset="UTF-8">
    <title>회원가입 성공</title>


	<!-- 5초 뒤, 로그인 메인화면으로 이동 -->

   <script type="text/javascript">
        setTimeout(function () {
            window.location.href = "<%=request.getContextPath()%>/Login";
        }, 5000);
    </script>
    
</head>

<body>
    <div id="successMesg">
        회원가입 성공
    </div>

    <div id="sitesShortCut">
        <a href="<%=request.getContextPath()%>/Login">로그인</a>
    </div>
</body>
</html>
