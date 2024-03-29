<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 비밀번호 찾기 페이지의 jsp -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/ID.css">

<head>
    <meta charset="UTF-8">
    <title>Find Password</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript">
        $(function () {
        	
        	$("#findPWForm").submit(function(event) {
        		//아이디 공백 여부 확인
        		if ($("#userId").val().trim() === "") {
    				alert("아이디를 확인해주세요");
    				$("#userId").focus();
    				return false;
    			}
    			
        		//이름 공백 여부 확인
        		if ($("#userName").val().trim() === "") {
    				alert("이름를 확인해주세요");
    				$("#userName").focus();
    				return false;
    			}
    			
        		//SSN 길이 여부 확인
    			if ($("#ssn1").val().length != 6 || $("#ssn2").val().length != 7) {
    				alert("주민등록번호를 확인해주세요");
    				$("#ssn1").focus();
    				return false;
    			}
    		})

    		//SSN1이 6자리까지 입력했을 경우, SSN2로 focus
    		$("#ssn1").on('input', function() {
    			var maxLength = 6;
    			if ($(this).val().length >= maxLength) {
    				$(this).val($(this).val().slice(0, maxLength));
    				$("#ssn2").val("");
    				$("#ssn2").focus();
    			}
    		});
        	
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
        	
        });
    </script>
   
</head>

<body>
    <h2>비밀번호 찾기</h2>
    <form id="findPWForm" action="<%=request.getContextPath()%>/SearchPartPW" method="post">
        아이디: <input type="text" class="must" id="userId" name="userId" pattern="[a-zA-Z0-9]{4,}" title="4자 이상의 영문 대소문자 또는 숫자를 입력하세요" autofocus><br>
        이름: <input type="text" class="must" id="userName" name="userName"><br>
        SSN: <input type="text" class="must" id="ssn1" name="ssn1" maxlength="6">
        - <input type="password" class="must" id="ssn2" name="ssn2" maxlength="7"><br>
        <input type="submit" value="확인">
    </form>
    <span id="sitesShortCut">
            <a href="<%=request.getContextPath()%>/Login">로그인</a> |
            <a href="<%=request.getContextPath()%>/FindID">아이디 찾기</a> |
            <a href="<%=request.getContextPath()%>/RegisterTerms">회원가입</a>
    </span>
    
</body>

</html>
