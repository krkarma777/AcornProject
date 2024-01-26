<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입 2단계에서 기존 회원인지 이름/SSN을 통해 확인하는 jsp -->

<head>
<meta charset="UTF-8">
<title>회원 확인 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 20px;
}

.container {
	max-width: 600px;
	margin: 0 auto;
}

label {
	display: block;
	margin-bottom: 10px;
}

.ssnContainer {
            display: flex;
            justify-content: space-between; 
            align-items: center; 
            margin-bottom: 10px;
        }

.ssn {
	width: 45%;
	padding: 10px;
	margin-bottom: 10px;
}

#userName {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
}

button {
	padding: 10px;
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>

<body>
	<div class="container">
		<h1>회원 확인 페이지</h1>
		<form id="confirmForm" action="<%=request.getContextPath()%>/SearchUser" method="post">
			<label for="name">성명</label> 
			<input type="text" id="userName" name="userName" required> 
			<label for="ssn1">주민등록번호</label>
			<div class="ssnContainer">
				<input type="number" class="ssn" id="ssn1" name="ssn1" maxlength="6" required>
				- <input type="password" class="ssn" id="ssn2" name="ssn2" maxlength="7" required><br>
			</div>
			<button type="submit">확인</button>
		</form>
	</div>
	
	<script type="text/javascript">
		
		$(function(){
			
			$("#confirmForm").submit(function(event) {
    			//이름 공백 확인 및 2글자
        		if ($("#userName").val().trim() === "" || $("#userName").val().length < 2) {
    				alert("이름를 확인해주세요");
    				$("#userName").focus();
    				return false;
    			}
    			
        		//SSN1 길이 확인
    			if ($("#ssn1").val().length != 6) {
    				alert("주민등록번호를 확인해주세요");
    				$("#ssn1").val('').focus();
    				return false;
    			}
        		
        		//SSN2의 첫 숫자 확인(1~4) + 길이 확인
        		var ssn2FirstNum = parseInt($("#ssn2").val().charAt(0));
        		if (ssn2FirstNum < 1 || ssn2FirstNum > 4 || $("#ssn2").val().length != 7) {
        			alert("주민등록번호를 확인해주세요");
    				$("#ssn2").val('').focus();
    				return false;
        		}
    			
    		})

    		//SSN1이 6자리 이상이면 SSN2로 focus
    		$("#ssn1").on('input', function() {
    			var maxLength = 6;
    			if ($(this).val().length >= maxLength) {
    				$(this).val($(this).val().slice(0, maxLength));
    				$("#ssn2").focus();
    			}
    		});
		})
		
		
	</script>
	
</body>

</html>