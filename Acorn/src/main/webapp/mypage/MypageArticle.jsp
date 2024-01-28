<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.ReviewDTO"%>
<%@page import="java.util.List"%>      
<%
   List<ReviewDTO> list = (List<ReviewDTO>)request.getAttribute("postList");
%>

<table border="1">
    <tr>
        <th>Post ID</th>
        <th>Post Board</th>
        <th>User ID</th>
        <th>Post Title</th>
        <th>Post Date</th>
        <th>Post Edit Date</th>
        <th>Post Text</th>
        <th>Cont ID</th>
        <th>Action</th>
    </tr>

<%
   for(int i = 0; i < list.size(); i++) {
        ReviewDTO post = list.get(i);
%>
        <tr>
            <td><%= post.getPostId() %></td>
            <td><%= post.getPostBoard() %></td>
            <td><%= post.getUserId() %></td>
            <td><%= post.getPostTitle() %></td>
            <td><%= post.getPostDate() %></td>
            <td><%= post.getEditDate() %></td>
            <td><%= post.getPostText() %></td>
            <td><%= post.getContId() %></td>
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