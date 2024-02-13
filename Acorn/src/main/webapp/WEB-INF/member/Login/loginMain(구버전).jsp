<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!--로그인 메인 페이지의 html-->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/Main.css">

<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
    <script type="text/javascript">
        $(function () {
			
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
			    
			    var userId = $("#userId").val();
                var userPw = $("#userPw").val();

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
				
			});	//$("#loginForm").on("submit", function(event)
			
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
                if (parts.length == 2){							//쿠키에 유저 아이디가 있는 경우
                	return parts.pop().split(";").shift();		//찾는 쿠키를 세미콜론을 기준으로 기준으로 자르기
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
            
            
            
        });	//$("#showPasswd").click(function ()
    </script>
</head>

<%
	MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");
	if(dto != null){
	System.out.println(dto.getUserId());}
%>

<body>

<div id="header">
    <jsp:include page="//common/navibarForMember.jsp"></jsp:include><br>
</div>

    <div id="for_Login">
        <form id="loginForm" action="<%=request.getContextPath()%>/Mypage" method="post">
            <table>
                <tr>
                    <td>아이디:</td>
                    <td><input type="text" id="userId" name="userId" class="loginSet" pattern="[a-zA-Z0-9]{4,}" autofocus autofocus autocomplete="off"></td>
					<td><input type="checkbox" id="userIdSave" name="userIdSave" class="loginSet">아이디 저장<br>
						<input type="checkbox" id="autoLogin" name="autoLogin">자동로그인<br>
						<span class="warning-icon">&#9888;</span>작동 확인 중<span class="warning-icon">&#9888;</span></td>
                </tr>
                <tr>
                </tr>
                <tr>
                    <td>비밀번호:</td>
                    <td><input type="password" id="userPw" name="userPw" class="loginSet" autocomplete="off"></td>
                    <td><button type="button" id="showPasswd">비밀번호 보이기</button><br>
                </tr>
                <tr>
                <td></td>
                	<td colspan="2">
                		<span id="confirmUserIdPwError" style="color: red;"></span>
                	</td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input id="loginButton" type="submit" value="로그인">
                    </td>
                </tr>
            </table>
        </form>
        <div id="sitesShortCut">
            <a href="<%=request.getContextPath()%>/FindID">아이디 찾기</a> |
            <a href="<%=request.getContextPath()%>/FindPW">비밀번호 찾기</a> |
            <a href="<%=request.getContextPath()%>/RegisterTerms">회원가입</a>
        </div>
        <div id="debugLink">
            <a href="<%=request.getContextPath()%>/memberListServlet">디버그용 - 회원리스트</a>
        </div>
    </div>
</body>

</html>