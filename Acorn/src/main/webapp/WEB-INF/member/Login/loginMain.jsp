<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/Main.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>
<body>

<%
	MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");
	if(dto != null){
	System.out.println(dto.getUserId());}
%>

<div id="header">
    <jsp:include page="/common/navibarForMember.jsp"></jsp:include><br>
</div>

<div id="contentBody">
	<div class="container" id="container">
	<!-- 회원가입 컨테이너 -->
	  <div class="form-container sign-up-container">
	    <form action="<%=request.getContextPath()%>/RegisterTerms">
	      <h1>회원가입</h1>
	      <div class="social-container">
	        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
	        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
	        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
	      </div>
	      <span>외부 사이트 이용하실껀가요?</span>
	      <input type="text" id="userName" name="userName" required placeholder="성함" />
	      <input type="text" class="ssn" id="ssn1" name="ssn1" maxlength="6" required placeholder="주민번호 앞자리"/>
	      <input type="password" class="ssn" id="ssn2" name="ssn2" maxlength="7" required placeholder="주민번호 뒷자리"/>
	      <button>회원가입으로</button>
	    </form>
	  </div>
	  
	<!-- 로그인 컨테이너 -->
	  <div class="form-container sign-in-container">
	    <form id="loginForm" action="<%=request.getContextPath()%>/Mypage" method="post">
	      <h1>로그인</h1>
	      <div class="social-container">
	        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
	        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
	        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
	      </div>
	      <span>외부 사이트를 이용하실껀가요?</span>
	      <input type="text" id="userId" name="userId" class="loginSet" pattern="[a-zA-Z0-9]{4,}" autofocus autocomplete="off" placeholder="아이디" />
	      <input type="password" id="userPw" name="userPw" class="loginSet" autocomplete="off" placeholder="패스워드"/>
	      <button type="button" id="showPasswd" class="loginButtons">비밀번호 보이기</button>
	      <button class="loginButtons">로그인</button>
	    
	      <div class="row" id="rowBar">
              <div class="col-2"><input type="checkbox" id="userIdSave" name="userIdSave" class="loginSet"></div>
              <div class="col-4 save-label">아이디 저장</div>
              <div class="col-2"><input type="checkbox" id="autoLogin" name="autoLogin"></div>
              <div class="col-4 auto-login-label">자동 로그인</div>
		  </div>	 
	      <div class="row">
              <div class="col"><a href="<%=request.getContextPath()%>/FindInfo">회원정보 찾기</a></div>
		  </div>	 
	   </form>
	  </div>

	<!-- 안내 문구 컨테이너 -->	  
	  <div class="overlay-container">
	    <div class="overlay">
	      <div class="overlay-panel overlay-left">
	        <h1>환영합니다!</h1>
	        <p>회원가입을 위해<br>고객님의 정보를 입력해주세요!</p>
	        <button class="ghost" id="signIn">로그인 화면으로</button>
	      </div>
	      <div class="overlay-panel overlay-right">
	        <h1>문화인의 밤에<br>어서오세요!</h1>
	        <p>사이트 이용을 위해<br>아이디와 비밀번호를 입력해주세요!</p>
	        <button class="ghost" id="signUp">회원가입 화면으로</button>
	      </div>
	    </div>
	  </div><!-- overlay-container -->
	</div><!-- container -->
</div><!-- contentBody -->


	<script type="text/javascript">
	$(function(){
	    
	    //로그인과 회원가입 탭 전환*******************************************
	    $('#signUp').on('click', function () {
	        $('#container').addClass("right-panel-active");
	        $('#userId').val('');
	        $('#userPw').val('');
	        $('#userName').focus();
	    });
	    
	    function changeTab(){
	    	$('#container').removeClass("right-panel-active");
	        $('#userName').val('');
	        $('#ssn1').val('');
	        $('#ssn2').val('');
	        $('#userId').focus();
	    }

	    $('#signIn').on('click', function () {
	    	changeTab()
	    });
	    //*************************************************************

	    
	    //로그인 관련 함수*************************************************
	    var currentAjaxRequest = null;
	    
	    //로그인 전송을 시도할 경우, 발동
	    $("#loginForm").on("submit", function(event) {
	        event.preventDefault(); // 폼이 서버로 전송되지 않도록 기본 동작을 막음
	        
	        document.cookie = "savedUserId=; expires=0; path=/";
	        document.cookie = "savedUserPw=; expires=0; path=/";
	        
	        // 아이디 저장 체크박스 상태에 따라 쿠키 생성
	        if ($("#userIdSave").prop("checked")) {
	            var userId = $("#userId").val();
	            //쿠키 유효기간 1일, 경로 지정은 보류(WEB-INF/member/Login/loginMain.jsp)
	            document.cookie = "savedUserId=" + userId + "; expires=" + getCookieExpiration(1) + "; path=/";
	        }
	        
	        // 자동로그인 체크박스 상태에 따라 쿠키 생성
	        if ($("#autoLogin").prop("checked")) {
	            var userId = $("#userId").val();
	            var userPw = $("#userPw").val();
	            //쿠키 유효기간 1일, 경로 지정은 보류(WEB-INF/member/Login/loginMain.jsp)
	            document.cookie = "savedUserId=" + userId + "; expires=" + getCookieExpiration(1) + "; path=/";
	            document.cookie = "savedUserPw=" + userPw + "; expires=" + getCookieExpiration(1) + "; path=/";
	        }
	        
	        // 이전 Ajax 요청이 진행 중이라면 취소(4면 요청이 완료되었음을 의미)
	        if (currentAjaxRequest && currentAjaxRequest.readyState !== 4) {
	            //현재 진행중인 ajax요청을 중단
	            currentAjaxRequest.abort();
	        }
	        
	        var userId = $("#userId").val();
	        var userPw = $("#userPw").val();
	        var errorSpan = $("#confirmUserIdPwError");

	        //아이디와 비밀번호를 모두 입력한 경우
	        if (userId && userPw) {
	            $.ajax({
	                type: "POST",
	                url: "<%=request.getContextPath()%>/AjaxCheckIDPWServlet", 
	                data: {
	                    userId: userId,
	                    userPw: userPw,
	                },
	                
	                beforeSend: function() {
	                    // AJAX 요청 전에 수행할 작업
	                    $("#loginButton").prop("disabled", true); // 버튼 비활성화
	                },
	               
	                success: function(response) {
	                    
	                    // 입력한 아이디와 비밀번호가 DB 정보와 일치하지 않을 경우, ajax 출력
	                    if (response === "loginFail") {
	                        errorSpan.text("아이디나 비밀번호를 확인해주세요.");
	                        
	                    // 입력한 아이디와 비밀번호가 DB 정보와 일치할 경우, submit 정상 작동
	                    } else {
	                        errorSpan.text("");
	                        $("#loginForm")[0].submit();
	                    }
	                },
	                error: function(error) {
	                    console.error("아이디, 비밀번호 검사 에러:", error);
	                },
	              
	                complete: function() {
	                    // AJAX 요청 완료 후 수행할 작업
	                    $("#loginButton").prop("disabled", false); // 버튼 활성화
	                }
	            });
	        } else {
	            errorSpan.text("");
	        }
	        
	        // ID 공백 여부 확인
	        if (userId.trim() === "") {
	            alert("아이디를 입력하세요");
	            $("#userId").focus();
	            return false;
	        }

	        // PW 공백 여부 확인
	        if (userPw.trim() === "") {
	            alert("비밀번호를 입력하세요");
	            $("#userPw").focus();
	            return false;
	        }
	        
	        //에러 메세지가 있는지 확인
	        if($("#confirmUserIdPwError").text() != ""){
	            $("#userId").focus();
	            return false;
	        }
	            
	        return true;
	        
	    }); //$("#loginForm").on("submit", function(event)
	    
	    //패턴 속성에 문구 삽입
	    $("#loginButton").on("click", function(){
	        console.log("Submit button clicked");
	        var inputValue = $("#userId").val();
	        
	        // 유효한 경우 아무 동작도 하지 않음
	        if (/^[a-zA-Z0-9]+$/.test(inputValue)) {
	            $("#userId")[0].setCustomValidity(''); 
	        } else {
	            // 유효하지 않은 경우 오류 메시지를 직접 설정
	            $("#userId")[0].setCustomValidity('영문자 또는 숫자를 입력하세요.');
	        }
	    })    
	    
	    //비밀번호를 볼 수 있도록 해주는 method
	    $("#showPasswd").click(function () {
	        var showPW = $("#userPw");
	        showPW.attr("type", showPW.attr("type") == "password" ? "text" : "password");
	    });

	    //쿠키 만료일 지정
	    function getCookieExpiration(days) {
	        var date = new Date();
	        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
	        return date.toUTCString();
	    }
	    
	    // 쿠키 불러오기
	    var savedUserId = getCookie("savedUserId");
	    var savedUserPw = getCookie("savedUserPw");

	    // 쿠키를 이름으로 가져오는 함수
	    function getCookie(name) {
	        var cookies = "; " + document.cookie;
	        var parts = cookies.split("; " + name + "=");
	        if (parts.length == 2){                            //쿠키에 유저 아이디가 있는 경우
	            return parts.pop().split(";").shift();        //찾는 쿠키를 세미콜론을 기준으로 기준으로 자르기
	        }
	    }
	    
	    // 쿠키가 존재하면 아이디 입력란에 표시
	    if (savedUserId) {
	        $("#userId").val(savedUserId);
	        $("#userIdSave").prop("checked", true);
	    }
	    
	    // 자동로그인으로 비밀번호 쿠기가 존재하면 비밀번호 입력칸에 표시
	    if (savedUserPw) {
	        $("#userPw").val(savedUserPw);
	        $("#autoLogin").prop("checked", true);
	    }
	    //*************************************************************
	    
	    
	    //기존 회원 여부 확인 관련 함수****************************************
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
	//*************************************************************

		
	</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
        crossorigin="anonymous"></script>  
</body>
</html>
