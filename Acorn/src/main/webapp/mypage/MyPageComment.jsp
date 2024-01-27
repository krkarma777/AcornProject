<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.CommentDTO"%>
<%@page import="java.util.List"%>    
<%
   List<CommentDTO> list = (List<CommentDTO>)request.getAttribute("commentList");
%>

<table border="1">
    <tr>
        <th>Comment ID</th>
        <th>Post ID</th>
        <th>User ID</th>
        <th>Comment Date</th>
        <th>Comment Text</th>
        <th>Above Comment</th>
        <th>Action</th>
    </tr>

<%
   for(int i = 0; i < list.size(); i++) {
        CommentDTO comment = list.get(i);
%>
        <tr>
            <td><%= comment.getComid() %></td>
            <td><%= comment.getPostid() %></td>
            <td><%= comment.getUserid() %></td>
            <td><%= comment.getComdate() %></td>
            <td><%= comment.getComtext() %></td>
            <td><%= comment.getAbovecom() %></td>
            <td>
                <button class="delete-button">Delete</button>
            </td>
        </tr>
<%
   }
%>


</table>

<!-- 스타일 추가 -->
<style>
    .delete-button {
        background-color: #ff6961; /* 적절한 색상 선택 */
        color: white;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
    }
</style>
