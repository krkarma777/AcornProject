<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입을 위한 약관에 동의하는 페이지.jsp -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/register_term.css">

<head>
    <meta charset="UTF-8">
    <title>약관 동의 페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>

	<%
		String userName = (String)request.getAttribute("userName"); 
		String ssn1 = (String)request.getAttribute("ssn1"); 
		String ssn2 = (String)request.getAttribute("ssn2"); 
		System.out.println("jsp:"+ userName);

	%>
	
	

    <div class="container">
        <h1>약관 동의 페이지</h1>

        <form id="agreementForm" action="<%=request.getContextPath()%>/CheckExistUser" method="post">
		
			<input type="hidden" name="userName" value="<%=userName%>">
			<input type="hidden" name="ssn1" value="<%=ssn1%>">>
			<input type="hidden" name="ssn2" value="<%=ssn2%>">

            <div>
                <textarea readonly="readonly">
                    이용 약관 내용
                </textarea>
                <label><input type="checkbox" class="checkboxes" name="checked_Agreement"> 
                    이용	약관에 동의합니다.</label>
            </div>

            <div>
                <textarea readonly="readonly">
                    개인정보 처리방침 내용
                </textarea>
                <label><input type="checkbox" class="checkboxes" name="checked_Info">
                    개인정보 처리방침에 동의합니다.</label>
            </div>

            <div>
                <textarea readonly="readonly">
                    회원 탈퇴 및 서비스 이용 중지 규정 내용
                </textarea>
                <label><input type="checkbox" class="checkboxes" name="checked_Withdraw"> 
                    회원 탈퇴 및 서비스 이용 중지에 동의합니다.</label>
            </div>
            <br>

            <div>
                <label><input type="checkbox" id="allCheckbox" onclick="clickAllChk(this.checked)"> 모두 동의합니다.</label>
            </div>
            <button type="button" onclick="chkAgreement()">다음 페이지로 이동</button>

        </form>
    </div>

    <script type="text/javascript">
        $(function() {

        	//모두 동의를 클릭하면 clickAllChk함수 발동(다른 체크박스가 모두 체크)
            $("#allCheckbox").click(function() {
                clickAllChk(this.checked);
            });

         	// 개별 약관에 대한 체크박스 클릭 시 모두 동의 체크박스 상태 갱신
            $(".checkboxes").click(function() {
                $("#allCheckbox").prop("checked", $(".checkboxes:checked").length === $(".checkboxes").length);
            });
        });

    	//모두 동의를 클릭하면 clickAllChk함수 발동(다른 체크박스가 모두 체크)
        function clickAllChk(tc) {
            $(".checkboxes").prop("checked", tc);
        }

    	//모두 동의를 제외한 모든 체크박스가 체크되지 않으면 다음 페이지로 이동 불가
        function chkAgreement() {
            if ($(".checkboxes:not(:checked)").length === 0) {
                alert("약관에 모두 동의하셨습니다. 다음 페이지로 이동합니다.");
                document.getElementById("agreementForm").submit();
            } else {
                alert("모든 약관에 동의해야 다음 페이지로 이동할 수 있습니다.");
            }
        }
    </script>

</body>
</html>
