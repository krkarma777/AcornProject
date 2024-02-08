<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 비밀번호 찾기에서 비밀번호를 찾을 경우, 나오는 페이지의 jsp -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member/ID.css">

<head>
    <meta charset="UTF-8">
    <title>Found PW</title>
    
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript">
    
    	//비밀번호의 절반을 "*"로 가리는 코드
        function maskPassword(password) {
            var visible = Math.ceil(password.length / 2);				// 마스킹할 길이 (비밀번호 길이의 절반)
            var masked = "*".repeat(password.length - visible);			// 마스킹 길이만큼 "*" 반복
            return password.slice(0, visible) + masked;
        }
        
     	// 전체 비밀번호 찾기 새 창 열기
         function openAllPWWindow(userId) {
            window.open("<%=request.getContextPath()%>/FindAllPW?userId="+userId, "전체 비밀번호 확인", "width=600,height=300");

        }
        
    </script>
</head>

<body>
    <%
    MemberDTO dto = (MemberDTO) request.getAttribute("foundUserPW");
    %>

    <h2>찾은 비밀번호 정보</h2>
    
    <%
    		//JavaScript 함수는 클라이언트 측에서 실행되므로, Java 코드에서 직접 호출할 수 없기 때문에 scrip 형식
            String maskedPassword = "<script>document.write(maskPassword('" + dto.getUserPw() + "'));</script>";
    %>
			<!-- 자식 창에 전달할 데이터를 숨겨진 input 태그로 설정 -->
		    <input type="hidden" id="userId" name="userId" value="<%=dto.getUserId()%>">
    
    		<p><%=dto.getUserName()%>님의 비밀번호는 <%=maskedPassword%>입니다.</p>
    		
    		<!-- 비밀번호 새창 찾기로 연결 -->
    		<button type="button" onclick="openAllPWWindow('<%=dto.getUserId()%>')">
                <%=dto.getUserName()%>님의 비밀번호 확인
            </button>
    <div id="sitesShortCut">
        <a href="<%=request.getContextPath()%>/Login">로그인</a> 
    </div>

</body>

</html>
