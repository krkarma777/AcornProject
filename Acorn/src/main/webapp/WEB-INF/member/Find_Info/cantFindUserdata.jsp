<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 아이디 찾기 실패 / 비밀번호 찾기 실패 / 모종의 이유로 로그인 진입에는 성공했으나 로그인 자체에는 실패했을 경우 -->

<head>
    <meta charset="UTF-8">
    <title>회원 가입 이력 없음</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
            text-align: center;
        }

        .notFound {
            max-width: 600px;
            margin: 0 auto;
        }

        .mesg {
            font-size: 18px;
            margin-bottom: 20px;
            color: red;
        }

        #sitesShortCut {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .links {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
        }
    </style>
</head>

<body>
    <div class="notFound">
        <h1>회원 가입 이력 없음</h1>
        <p class="mesg">회원 정보가 없습니다. 입력하신 정보를 다시 한번 확인해주세요.</p>
        <div id="sitesShortCut">
            <a href="<%=request.getContextPath()%>/Login" class="links">로그인</a>
            <a href="<%=request.getContextPath()%>/FindID" class="links">아이디 찾기</a>
            <a href="<%=request.getContextPath()%>/FindPW" class="links">비밀번호 찾기</a>
            <a href="<%=request.getContextPath()%>/RegisterTerms" class="links">회원가입</a>
        </div>
    </div>
</body>

</html>
