<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입에 성공할 경우, 나타나는 페이지의 jsp -->

<head>
    <meta charset="UTF-8">
    <title>회원가입 성공</title>
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

        #successMesg {
            background-color: #4CAF50;
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
        }
    </style>

	<!-- 5초 뒤, 로그인 메인화면으로 이동 -->
    <script type="text/javascript">
        setTimeout(function () {
            window.location.href = "<%=request.getContextPath()%>/LoginForm_Active";
        }, 5000);
    </script>
    
</head>

<body>
    <div id="successMesg">
        회원가입 성공
    </div>

    <div id="sitesShortCut">
        <a href="<%=request.getContextPath()%>/LoginServlet">로그인</a>
    </div>
</body>
</html>
