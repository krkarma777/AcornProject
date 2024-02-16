<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입 2단계에서 기존 회원인지 이름/SSN을 통해 확인하는 jsp -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/SSN.css">


<head>
<meta charset="UTF-8">
<title>회원 확인 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


</head>

<body>
	<div class="container">
		<h1>회원 확인 페이지</h1>
		<form id="confirmForm" action="<%=request.getContextPath()%>/SearchUser" method="post">
			<label for="name">성명</label> 
			<input type="text" id="userName" name="userName" required autofocus> 
			<label for="ssn1">주민등록번호</label>
			<div class="ssnContainer">
				<input type="text" class="ssn" id="ssn1" name="ssn1" maxlength="6" required>
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
        		if ($("#ssn2").val().length != 7) {
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
    				$("#ssn2").val("");
    				$("#ssn2").focus();
    			}
    		});
		})
		
		//ssn1 숫자로 입력 제한
		$("#ssn1").on("input", function () {
				
			if (!/^\d*$/.test($(this).val())) {
				alert("숫자만 입력 가능합니다.");
				$(this).val("");
				$(this).focus();
			}
		});

		//ssn2 숫자로 입력 제한
		$("#ssn2").on("input", function () {
			
			if (!/^\d*$/.test($(this).val())) {
				alert("숫자만 입력 가능합니다.");
				$(this).val("");
				$(this).focus();
			}
		});
		
		
		
	</script>
	
</body>

</html>