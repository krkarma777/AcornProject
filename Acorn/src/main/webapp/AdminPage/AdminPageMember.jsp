<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 시작화면 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">

</script>
</head>
<body>

<div id = "top" align = "center">
	<jsp:include page = "../common/top.jsp" flush ="true" ></jsp:include>
</div>

<div id = "menu" align = "center">
<jsp:include page = "AdminMenu.jsp"  flush ="true"></jsp:include>
</div>

<div id = "content" align = "center">
<jsp:include page = "./member/AdminMember.jsp"  flush ="true"  ></jsp:include>
</div>

<%-- <jsp:include page = "AdminMember.jsp"  flush ="true" ></jsp:include> --%>

<script type = "text/javascript">
$(document).ready(function(){
	
})

</script>

</body>
</html>
