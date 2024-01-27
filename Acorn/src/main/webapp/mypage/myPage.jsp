<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.MemberDTO"%>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	//form 서브밋
 $("form").on("submit",function(event){		
	 var userid = $("#userid").val();
	 var passwd = $("#userpw").val();
    		if(userid.length==0){
    			alert("userid 필수")
    			$("#userid").focus();
    			event.preventDefault();
    		}else if(passwd.length==0){
    			alert("passwd 필수")
    			$("#userpw").focus();
    			event.preventDefault();
    		}
    	});
//비번확인
 $("#userpw2").on("keyup",function(){
		var passwd = $("#userpw").val();
		var mesg = "비번 불일치";
		if(passwd == $(this).val()){
			mesg = "비번 일치";
		}
		$("#result2").text(mesg);
	});
	
//이메일 선택
 $("#emailSelect").on("click",function(){
		var email = $(this).val();
		  $("#USEREMAILDOMAIN").val(email);
	});
	
 $("#userid").on("keyup",function(event){	
	 $.ajax({
			type : "GET",
			url : "MemberIdCheckServlet",
			dataType : "text",
			data : {
				userid : $("#userid").val()
			},
			success : function(responseData, status, xhr) {
			    $("#result").text(responseData);
			},
			error : function(xhr, status, error) {
				console.log("error");
			}
		});
});
 
 });
</script>    
<%
MemberDTO dto = (MemberDTO)request.getAttribute("loginUser");
String userid=dto.getUserId();
String userpw =  dto.getUserPw();
String userName =  dto.getUserName();
int userssn1= dto.getUserSSN1();
int userssn2 = dto.getUserSSN2();
String usergender = dto.getUserGender();
String NICKNAME = dto.getNickname();
String USERPHONENUM1 = dto.getUserPhoneNum1();
String USERPHONENUM2 = dto.getUserPhoneNum2();
String USERPHONENUM3 = dto.getUserPhoneNum3();
String USEREMAILID = dto.getUserEmailId();
String USERSIGNDATE = dto.getUserSignDate();
String USERTYPE = dto.getUserType();
%>
개인정보
<hr>
<form action="MemberUpdateServlet" method="post">
    <input type="hidden" value="<%= userid %>" name="userid">
    <div>
        아이디 : <span id="show_id"><%= userid %></span>
        <br>
        <br>
        닉네임 : <input type="text" value="<%= NICKNAME %>"  id="nickname"> <button>수정하기</button> <span id="nickname_chk"></span>
        <br>
        <br>
        이메일 : <input type="text" value="<%= USEREMAILID %>" name="USEREMAILID" id="USEREMAILID">@
        <br> <span id="email_chk"></span>
        <br>
        전화번호 : <span id="show_number" value="<%= USERPHONENUM1 %>" name="USERPHONENUM1"><%= USERPHONENUM1 %>-<%= USERPHONENUM2 %>-<%= USERPHONENUM3 %></span> <button>수정하기</button>
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
            <input type="radio" name="marketing_phone">동의
        </label>
        <label>
            <input type="radio" name="marketing_phone">비동의
        </label>
        <br>
        <br>
        이메일 :
        <label>
            <input type="radio" name="marketing_email">동의
        </label>
        <label>
            <input type="radio" name="marketing_email">비동의
        </label>
    </div>
    <br>
    <br>
    <br>
<input type="submit" value="수정"><input type="reset" value="취소">
</form>

