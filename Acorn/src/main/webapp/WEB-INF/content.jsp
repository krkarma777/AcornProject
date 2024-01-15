<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Content</title>
</head>
<body>
	<%--  글 제목 --%>
	<c:out value="${title}"/>
    <%--  글 내용 --%>
    <c:out value="${postText}" escapeXml="false" />
</body>
</html>
