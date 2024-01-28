<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
개인정보
<hr>
<div>
아이디 : <span id = "show_id">id 예시</span>
<br>
<br>
닉네임 : <input type = "text" placeholder = "닉네임예시1" id = "nickname"> <button>수정하기</button> <span id = "nickname_chk"></span>
<br>
<br>
이메일 : <input type = "text" placeholder = "이메일예시1" id = "email"> <button>수정하기</button> <span id = "email_chk"></span>
<br>
<br>
주소 : <span id = "show_address">주소 예시입니다</span> <button>수정하기</button>
<br>
<br>
<!-- 수정하기 누르면 Daum주소 API 활용해서 전송 -->
전화번호 : <span id = "show_number">전화번호 예시입니다</span> <button>수정하기</button>
<!-- 전화번호 인증 API확인 -->
</div>
<br>
<br>
<br>
<br>
정보수신 동의
<hr>
<div>
SMS : 

<label>
<input type = "radio" name = "marketing_phone">동의
</label>

<label>
<input type = "radio" name = "marketing_phone">비동의
</label>
<br>
<br>
이메일 : 

<label>
<input type = "radio" name = "marketing_email">동의
</label>

<label>
<input type = "radio" name = "marketing_email">비동의
</label>
</div>

<br>
<br>
<br>
<button>저장</button> <button>취소</button>


</body>
</html>