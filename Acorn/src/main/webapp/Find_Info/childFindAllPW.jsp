<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<!-- 전체 비밀번호를 확인하는 jsp -->

<head>
<meta charset="UTF-8">
<title>전체 비밀번호 확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

form {
    text-align: center;
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin: 20px; 
}

label {
	display: block;
	margin-bottom: 10px;
}

input {
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

button {
	padding: 8px 16px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

button:hover {
	background-color: #0056b3;
}

#confirmIdError {
	color: red;
}
</style>

<script type="text/javascript">

	//userId를 받아 처리하는 함수
	 var userId = opener.$("#userId").val();
																		//디버그 코드**********
																		console.log(userId);
																		//디버그 코드**********	
	$(function () {
		
		//진행 중인 Ajax 요청을 저장할 변수
		var currentAjaxRequest = null;
		
		//////////////////////////////////////////////////////////////////
		// 사용자 답변 확인 함수
		function checkUserAnswer(event) {
		    // 이전 Ajax 요청이 진행 중이라면 취소(4면 요청이 완료되었음을 의미)
		    if (currentAjaxRequest && currentAjaxRequest.readyState !== 4) {
		        //현재 진행중인 ajax요청을 중단
		    	currentAjaxRequest.abort();
		    }
		}
		//////////////////////////////////////////////////////////////////
		
		$("#confirmUserInfo").change(function () {
			var userInfo = $(this).val();
			var userAnswerInput = $("#userAnswer");
																		//디버그 코드**********			
																		console.log(userInfo)
																		//디버그 코드**********			
			userAnswerInput.val("");
			userAnswerInput.focus();

			// 질문이 핸드폰 번호에 대한 것일 경우, 숫자 11자리로 제한, 숫자만 허용
			if (userInfo == "userPhoneNum") {
				
				// 입력값이 숫자가 아닌 경우 경고창을 띄움
				userAnswerInput.on("input", function () {
					if (!/^\d*$/.test($(this).val())) {
						alert("숫자만 입력 가능합니다.");
						$(this).val("");
						$(this).focus();
					}
				});
				
				userAnswerInput.attr({
					"maxlength": "11",
					"pattern": "\\d*"
				});
			 } else {
                 // userInfo가 userPhoneNum이 아닌 경우에는 이벤트 제거
                 userAnswerInput.off("input");
                 userAnswerInput.removeAttr("maxlength pattern");
             }
		});
	});																		
																		
	//질문에 따른 대답과 관련된 ajax와 method
	function checkUserAnswer(event) {
		
		 var userInfo = $("#confirmUserInfo").val();
         var answer = $("#userAnswer").val();
         var errorSpan = $("#confirmAnswerError");
         
         //핸드폰 번호를 찾는 질문일 때, 숫자 11자리가 아닌 경우, 경초창 + 이벤트 중지
         if (userInfo === "userPhoneNum" && !/^\d{11}$/.test(answer)) {
             alert("핸드폰 번호는 숫자 11자리여야 합니다.");
             event.preventDefault();
             return false;
             
         //이메일 주소를 찾는 질문일 때, @가 포함되어 있지 않은 경우, 경초창 + 이벤트 중지
         } else if (userInfo === "userEmail" && !/@/.test(answer)) {
             alert("이메일 주소는 @를 포함해야 합니다.");
             event.preventDefault();
             return false;
         } else {
             
			//질문에 대한 답변 타당성 판정
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/AjaxMatchQnAServlet",
				data : {
					userInfo : userInfo,
					answer : answer,
					userId: userId
				},
				
				beforeSend: function() {
                    // AJAX 요청 전에 수행할 작업 (로딩 표시 등)
                    $("#check_answer").prop("disabled", true); // 버튼 비활성화
                },
                
				success : function(response) {
					
					//질문에 따른 해당 유저의 답변이 올바르지 않을 경우, ajax출력
					if (response == "wrong_Answer") {
						errorSpan.text("질문에 대한 답변이 올바르지 않습니다.");
						
					//질문에 따른 해당 유저의 답변이 올바를 경우, 자식창을 닫고 부모창으로 이동시키는 함수 발동
					} else if (response == "correct_Answer") {
						moveToParentPage();
					}
				},
				error : function(error) {
					console.error("비밀번호 출력 검사 에러:", error);
				}, 
				
				complete: function() {
                    // AJAX 요청 완료 후 수행할 작업 (로딩 표시 해제 등)
                    $("#check_answer").prop("disabled", false); // 버튼 활성화
                }
				
			});
		}
	}
	
	//질문에 따른 해당 유저의 답변이 올바를 경우, 자식창을 닫고, 부모창을 전체 비밀번호를 보여주는 페이지로 이동
	function moveToParentPage() {
		window.opener.location.href = "<%=request.getContextPath()%>/SearchAllPWServlet?userId=" + userId;
        window.close(); 
    }
	
	
	
</script>

</head>

<body>

	<form id="confirmForm">
        <label for="confirmUserId">전체 비밀번호 확인을 위한 질문</label>
        <select id="confirmUserInfo" name="confirmUserInfo">
            <option value="nickname">본인의 닉네임은?</option>
            <option value="userPhoneNum">본인의 핸드폰 번호는?(숫자만 쓰시오)</option>
            <option value="userEmail">본인의 이메일 주소는?</option>
        </select>
        <input type="text" id="userAnswer" name="userAnswer">
        <button id="check_answer" type="button" onclick="checkUserAnswer(event)">확인</button><br>
        <span id="confirmAnswerError"></span>
    </form>

</body>

</html>