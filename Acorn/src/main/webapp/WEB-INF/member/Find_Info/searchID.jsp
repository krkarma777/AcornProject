<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 아이디 찾기 페이지의 jsp -->

<head>
<meta charset="UTF-8">
<title>Find ID</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#findIdForm").submit(function(event) {
			//이름 공백 확인
			if ($("#userName").val().trim() === "") {
				alert("이름를 확인해주세요");
				event.preventDefault();
				$("#userName").focus();
				return false;
			}
			
			//SSN 길이 확인
			if ($("#ssn1").val().length != 6 || $("#ssn2").val().length != 7) {
				alert("주민등록번호를 확인해주세요");
				event.preventDefault();
				$("#ssn1").focus();
				return false;
			}
		})

		//SSN1이 6자리까지 입력했을 경우, SSN2로 focus
		$("#ssn1").on('input', function() {
			var maxLength = 6;
			if ($(this).val().length >= maxLength) {
				$(this).val($(this).val().slice(0, maxLength));
				$("#ssn2").focus();
			}
		});

	});
</script>
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

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            text-align: center;
        }

        input {
            margin-bottom: 10px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        #sitesShortCut {
            margin-top: 20px;
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

        #sitesShortCut a:hover {
            background-color: #0056b3;
        }
    </style>

</head>

<body>
	<h2>아이디 찾기</h2>
	<form id="findIdForm" action="<%=request.getContextPath()%>/SearchID" method="post">
		이름: 	<input type="text" class="must" id="userName" name="userName"><br>
		SSN: 	<input type="number" class="must" id="ssn1" name="ssn1" maxlength="6"> 
		- 		<input type="password" class="must"
				id="ssn2" name="ssn2" maxlength="7">
		<br> 	<input type="submit" value="확인">
	</form>
	<div id="sitesShortCut">
		<a href="<%=request.getContextPath()%>/Login">로그인</a> | 
		<a href="<%=request.getContextPath()%>/FindPW">비밀번호 찾기</a> | 
		<a href="<%=request.getContextPath()%>/RegisterTerms">회원가입</a>
	</div>



</body>

</html>
