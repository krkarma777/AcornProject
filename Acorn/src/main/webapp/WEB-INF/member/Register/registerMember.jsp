<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입 3단계로 유저 정보를 입력하는 페이지의 jsp -->

<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 20px;
	text-align: center;
}

.container {
	max-width: 400px;
	margin: 0 auto;
}

form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

label {
	margin: 10px 0;
}

input, select {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	box-sizing: border-box;
}

#userSSN1, #userSSN2 {
	width: 45%;
}

#userId, #userName, #userSSN1, #userSSN2 {
	background-color: #f0f0f0;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
}

.loadingSpinner {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            border: 4px solid rgba(0, 0, 0, 0.1);
            border-left: 4px solid #3498db;
            border-radius: 50%;
            width: 40px;
            height: 40px;
            animation: spin 1s linear infinite;
        }

@keyframes spin {
    0% {
        transform: translate(-50%, -50%) rotate(0deg);
    }

    100% {
        transform: translate(-50%, -50%) rotate(360deg);
    }
}

</style>
</head>

<body>

	<%
		String userName = (String) request.getAttribute("userName");
		String ssn1 = (String)request.getAttribute("ssn1");
		String ssn2 = (String)request.getAttribute("ssn2");
	%>

	<div class="container">
		<h1>회원가입</h1>
		<form id="registerForm" action="<%=request.getContextPath()%>/InsertData" method="post">
			
			<!-- 아이디 입력칸(영어+숫자로 4글자 이상)(반드시 입력되어야 함)(직접 입력은 불가하며, 자식창을 통해서만 입력 가능) -->
			<label for="userId">아이디 (영어 + 숫자, 최소 4글자)</label> 
				<input type="text" id="userId" name="userId" pattern="[a-zA-Z0-9]{4,}" required readonly>
				<button id = "userIdButton" type="button" onclick="openIdWindow()">아이디 확인</button>

			<!-- 비밀번호 입력칸(6글자 이상)(반드시 입력되어야 함) -->
			<label for="userPw">비밀번호 (최소 6글자)</label> 
				<input type="password" id="userPw" name="userPw" minlength="6" required> 

			<!-- 비밀번호 재입력칸(6글자 이상)(반드시 입력되어야 함) -->
			<label for="userPwConfirm">비밀번호 재입력</label> 
				<input type="password" id="userPwConfirm" name="userPwConfirm" minlength="6" required>
				<!-- 비밀번호와 비밀번호 재입력이 상이할 경우, 문구 출력 -->
				<span id="pwMismatch" style="color: red;"></span> 
			
			<!-- 유저 이름 입력칸(회원가입 2단계에서 입력한 값을 통해 입력)(직접 입력 불가) -->
			<label for="userName">유저 이름</label> 
				<input type="text" id="userName" name="userName" value="<%=userName%>" readonly> 
				
			<!-- 유저 닉네임 입력칸(최소 2글자 이상)(반드시 입력) -->
			<label for="nickname">유저 닉네임 (최소 2글자)</label> 
				<input type="text" id="nickname" name="nickname" minlength="2" required>
				<!-- DB에 저장된 닉네임이 있을 경우, 문구 출력 --> 
				<span id="confirmNicknameError" style="color: red;"></span>
				<span id="loadingSpinner_for_nickname" class="loadingSpinner"></span>

			<!-- 유저 SSN 입력칸(회원가입 2단계에서 입력한 값을 통해 입력)(직접 입력 불가) -->
			<label for="userSSN1">주민등록번호</label>
				<div style="display: flex; gap: 5px;">
					<input type="text" id="userSSN1" name="userSSN1" value="<%=ssn1%>" readonly> 
						&nbsp;&nbsp;-&nbsp;&nbsp; 
					<input type="password" id="userSSN2" name="userSSN2" value="<%=ssn2%>" readonly>
				</div>

			<!-- 유저 성별 Select칸(반드시 입력)(기본값 남성) -->
			<label for="userGender">성별</label> 
				<select id="userGender" name="userGender" required>
					<option value="male" selected>남성</option>
					<option value="female">여성</option>
				</select> 
			
			<!-- 유저 핸드폰 번호(반드시 입력)(첫 번째 3자리는 seleect)(두 번째/세 번째는 최대 4자리까지 직접 입력) -->
			<label for="userPhoneNum1">핸드폰 번호</label>
				<div style="display: flex; gap: 5px;">
					<select id="userPhoneNum1" name="userPhoneNum1" class="phoneNum" required>
				        <option value="010" selected>010</option>
				        <option value="011">011</option>
   					 </select> 
					<input type="text" id="userPhoneNum2" name="userPhoneNum2" class="phoneNum" required maxlength="4"> 
					<input type="text" id="userPhoneNum3" name="userPhoneNum3" class="phoneNum" required maxlength="4">
				</div>
				<!-- 핸드폰 번호란에 숫자만 입력되어 있지 않은 경우, 문구 출력 -->
				<span id="confirmPhoneNumError_notNumber" style="color: red;"></span>
				<!-- DB에 저장된 전체 핸드폰 번호가 일치하는 데이터가 있을 경우, 문구 출력 -->
				<span id="confirmPhoneNumError" style="color: red;"></span>
				<span id="loadingSpinner_for_PhoneNum" class="loadingSpinner"></span>

			<!-- 유저 이메일(반드시 입력)(select를 통해 도메인 입력 가능) -->
			<label for="userEmailDomain">이메일</label>
				<div style="display: flex; gap: 5px;">
					<input type="text" id="userEmailId" name="userEmailId" class ="userEmail" required>
						@ 
					<input type="text" id="userEmailDomain" name="userEmailDomain" class ="userEmail" required> 
					<select id="domainSelect" name="domainSelect" class ="userEmail" onchange="domainSelectMethod(this.value)">
						<option value="" selected>직접 입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
					</select>
				</div>
				<!-- 이메일 아이디에 영어나 숫자 외 다른 것이 입력될 경우, 문구 출력 -->
				<span id="confirmUserEmailIdError" style="color: red;"></span>
				<!-- DB에 저장된 이메인 아이디 + 이메일 도메인이 있을 경우, 문구 출력 -->
				<span id="confirmUserEmailError" style="color: red;"></span>
				<span id="loadingSpinner_for_Email" class="loadingSpinner"></span>
				

			<button id="register_button" type="submit">가입</button>
		</form>
	</div>


	<script type="text/javascript">
		//ID 새창 열기
		function openIdWindow() {
			var popup = window.open("<%=request.getContextPath()%>/IdDupilicate", "아이디 확인", "width=400,height=200");
			
			popup.onbeforeunload = function() {
				var confirmedUserId = popup.$("#confirmUserId").val();
				$("#userId").val(confirmedUserId);
			};
		}

		
		//닉네임 중복 확인
		$("#nickname").on("input", function() {
			var nickname = $("#nickname").val();
		    var errorSpan = $("#confirmNicknameError");
			
			$.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/AjaxNicknameDuplicateServlet", 
                data: { nickname: nickname },
                
                beforeSend: function () {
                    // AJAX 요청 전에 로딩 표시 보여주기
                	$("#loadingSpinner_for_nickname").show();
                	// 가입 버튼 비활성화
                	$("#register_button").prop("disabled", true);
                	$("#userIdButton").prop("disabled", true);
              },
                
                success: function (response) {
                	//닉네임이 DB에 저장된 닉네임과 일치하는 데이터가 있을 경우, ajax 출력
                    if (response === "duplicate") {
                        errorSpan.text("이미 사용 중인 닉네임입니다.");
                    } else {
                        errorSpan.text("");
                    } 
                },
                error: function (error) {
                    console.error("닉네임 중복 검사 에러:", error);
                }, 
                
                complete: function () {
                    // AJAX 요청 완료 후에 로딩 표시 숨기기
                	$("#loadingSpinner_for_nickname").hide();
                	// 가입 버튼 활성화
	               	$("#register_button").prop("disabled", false);
	               	$("#userIdButton").prop("disabled", false);
                }

            });
		});
		
		
		//핸드폰 번호를 숫자만 가능하도록 제한
		function isNumeric(value) {
			return /^\d+$/.test(value);
		}
		
		
		//두 번쨰 핸드폰 번호 4자리를 입력하면, 자동으로 세 번쨰 핸드폰 번호로 focus
		$("#userPhoneNum2").on('input', function() {
			var maxLength = 4;
			if ($(this).val().length >= maxLength) {
				$(this).val($(this).val().slice(0, maxLength));
				$("#userPhoneNum3").focus();
			}
		});
		
		
		//핸드폰 번호 중복 확인
		$(".phoneNum").on("input", function() {
		    var userPhoneNum1 = $("#userPhoneNum1").val();
		    var userPhoneNum2 = $("#userPhoneNum2").val();
		    var userPhoneNum3 = $("#userPhoneNum3").val();
		    var errorSpan = $("#confirmPhoneNumError");
		
		    //핸드폰 번호를 모두 입력했을 때 발동
		    if (userPhoneNum1 && userPhoneNum2 && userPhoneNum3) {
		        $.ajax({
		            type: "POST",
		            url: "<%=request.getContextPath()%>/AjaxPhoneNumDuplicateServlet", 
		            
		            beforeSend: function () {
	                    // AJAX 요청 전에 로딩 표시 보여주기
	                	$("#loadingSpinner_for_PhoneNum").show();
	                	// 가입 버튼 비활성화
	                	$("#register_button").prop("disabled", true);
	                	$("#userIdButton").prop("disabled", true);
	                },
		            
		            data: {
		            	userPhoneNum1: userPhoneNum1,
		            	userPhoneNum2: userPhoneNum2,
		            	userPhoneNum3: userPhoneNum3
		            },
		            success: function(response) {
		            	//핸드폰 번호 전체가 DB에 저장된 핸드폰 번호와 일치하는 데이터가 있을 경우, ajax 출력
		                if (response === "duplicate") {
		                	errorSpan.text("이미 사용 중인 핸드폰 번호입니다.");
		                } else {
		                	errorSpan.text("");
		                }
		            },
		            error: function(error) {
		                console.error("핸드폰 번호 중복 검사 에러:", error);
		            },
		            
		            complete: function () {
	                    // AJAX 요청 완료 후에 로딩 표시 숨기기
	                	$("#loadingSpinner_for_PhoneNum").hide();
	                	// 가입 버튼 활성화
		               	$("#register_button").prop("disabled", false);
		               	$("#userIdButton").prop("disabled", false);
	                }
		        });
		    } else {
		    	errorSpan.text("");
			}
		});
		
		
		//도메인 readonly
		function domainSelectMethod(tv) {
			//직접 입력 토글이 아닐 때는 도메인을 select로만 입력할 수 있도록 지정
			var userEmailDomain = $("#userEmailDomain");
			if (tv == "") {
				userEmailDomain.val("").prop("readonly", false);
			} else {
				userEmailDomain.val(tv).prop("readonly", true);
			}
		}
		
		
	    // 이메일 아이디에 영어와 숫자만 입력되도록 제한
	    $("#userEmailId").on("input", function() {
	        var userEmailId = $(this).val();
	        var errorSpan = $("#confirmUserEmailIdError");

	        var pattern = /^[a-zA-Z0-9]+$/;		// 영어와 숫자만 허용

	        if (!pattern.test(userEmailId)) {	// 영어나 숫자 외 다른 것이 입력될 경우
	            errorSpan.text("영어와 숫자만 입력 가능합니다.");
	        } else {
	            errorSpan.text("");
	        }
	    });
		
	    
		//이메일 중복 확인(아이디/도메인을 입력할 경우)
		$(".userEmail").on("input", function() {
		    var userEmailId = $("#userEmailId").val();
		    var userEmailDomain = $("#userEmailDomain").val();
		    var errorSpan = $("#confirmUserEmailError");
		
		    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
		    if (userEmailId && userEmailDomain) {
		        $.ajax({
		            type: "POST",
		            url: "<%=request.getContextPath()%>/AjaxEmailDuplicateServlet", 
		            
		            beforeSend: function () {
	                    // AJAX 요청 전에 로딩 표시 보여주기
	                	$("#loadingSpinner_for_Email").show();
	                	// 가입 버튼 비활성화
	                	$("#register_button").prop("disabled", true);
	                	$("#userIdButton").prop("disabled", true);
                },
		            
		            data: {
		            	userEmailId: userEmailId,
		            	userEmailDomain: userEmailDomain,
		            },
		            success: function(response) {
		            	//이메일 아이디와 도메인이 DB에 저장된 값과 일치할 경우, ajax 출력
		                if (response === "duplicate") {
		                	errorSpan.text("이미 사용 중인 이메일입니다.");
		                } else {
		                	errorSpan.text("");
		                }
		            },
		            error: function(error) {
		                console.error("이메일 중복 검사 에러:", error);
		            }, 
		            
		            complete: function () {
	                    // AJAX 요청 완료 후에 로딩 표시 숨기기
	                	$("#loadingSpinner_for_Email").hide();
	                	// 가입 버튼 활성화
		               	$("#register_button").prop("disabled", false);
		               	$("#userIdButton").prop("disabled", false);
	                }
		            
		        });
		    } else {
		    	errorSpan.text("");
			}
		});
		
		
		//이메일 중복 확인(도메인 선택할 경우)
		$("#domainSelect").on("change", function() {
		    var userEmailId = $("#userEmailId").val();
		    var userEmailDomain = $("#userEmailDomain").val();
		    var errorSpan = $("#confirmUserEmailError");
		
		    //이메일 아이디와 이메일 도메인이 모두 있을 때 출력
		    if (userEmailId && userEmailDomain) {
		        $.ajax({
		            type: "POST",
		            url: "<%=request.getContextPath()%>/AjaxEmailDuplicateServlet", 
		            data: {
		            	userEmailId: userEmailId,
		            	userEmailDomain: userEmailDomain,
		            },
		            success: function(response) {
		            	//이메일 아이디와 도메인이 DB에 저장된 값과 일치할 경우, ajax 출력
		                if (response === "duplicate") {
		                	errorSpan.text("이미 사용 중인 이메일입니다.");
		                } else {
		                	errorSpan.text("");
		                }
		            },
		            error: function(error) {
		                console.error("이메일 중복 검사 에러:", error);
		            }
		        });
		    } else {
		    	errorSpan.text("");
			}
		});
		
		
		//submit 제한규칙
		$("#registerForm").submit(function(event) {
			return validateForm(event);
		});

		
		function validateForm(event) {
			
			if ($(".loadingSpinner").is(":visible")) {
                alert("잠시만 기다려주세요");
                return false;
            }
			
			//아이디가 공백이면 경고창 + 전송 중지 + 아이디 새창 버튼 focus
			if($("#userId").val() == ""){
				alert("아이디를 입력해주세요");
				$("#userIdButton").focus();
				return false;
			}
			
			//비밀번호와 재입력된 비밀번호가 일치하는지 확인
			var password = $("#userPw").val();
			var confirmPassword = $("#userPwConfirm").val();

			//일치하지 않을 경우, 경고창 + 전송 중지 + 비밀번호 focus
			if (password !== confirmPassword) {
				$("#pwMismatch").text("입력한 비밀번호가 일치하지 않습니다.");
				alert("비밀번호 일치 여부를 확인해주세요");
				$("#userPw").focus();
				return false;
			} else {
				$("#pwMismatch").text("");
			}

			//닉네임 중복 ajax가 출력된 경우, 경고창 + 전송 중지 + 닉네임 focus
			if($("#confirmNicknameError").text() != ""){
				alert("닉네임 중복 여부를 확인해주세요");
				$("#nickname").focus();
				return false;
			}
			
			//핸드폰 번호에 숫자만 입력되어 있지 않은 경우, 경고창 + 전송 중지 + 핸드폰 번호 focus
			var phoneNum2 = $("#userPhoneNum2").val();
			var phoneNum3 = $("#userPhoneNum3").val();

			if (!isNumeric(phoneNum2) || !isNumeric(phoneNum3)) {
				$("#confirmPhoneNumError_notNumber").text("핸드폰 번호에는 숫자만 입력해주세요.");
				alert("핸드폰 번호를 확인해주세요");
				$("#userPhoneNum2").focus();
				return false;
			} else {
				$("#confirmPhoneNumError_notNumber").text("");
			}

			//핸드폰 번호 중복 ajax가 출력된 경우, 경고창 + 전송 중지 + 핸드폰 번호 focus
			if($("#confirmPhoneNumError").text() != ""){
				alert("핸드폰 번호 중복 여부를 확인해주세요");
				$("#userPhoneNum2").focus();
				return false;
			}
			
			//이메일 중복 ajax가 출력된 경우, 경고창 + 전송 중지 + 이메일 아이디 focus
			//이메일 아이디에 영어나 숫자가 아닌 문자가 들어간 경우, 경고창 + 전송 중지 + 이메일 아이디 focus
			if($("#confirmUserEmailError").text() != "" || $("#confirmUserEmailIdError").text() != ""){
				alert("이메일을 재검토해주세요");
				$("#userEmailId").focus();
				return false;
			}
				
			return true;
		}
	</script>



</body>

</html>
