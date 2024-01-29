<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.ReviewDTO"%>
<%@page import="java.util.List"%> 
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">     
<%
   List<ReviewDTO> list = (List<ReviewDTO>)request.getAttribute("reviewList");
%>
<br>
<br>
<div style="text-align: center;">
<h1> 내 게시글</h1>
</div>
<br>
<br>
<table class="table table-striped" border="1">
    <tr>
        <th>Post ID</th>
        <th>Post Board</th>
        <th>User ID</th>
        <th>Post Title</th>
        <th>Post Date</th>
        <th>Post Text</th>
        <th>Cont ID</th>
        <th>Delete</th>
    </tr>

<%
   for(int i = 0; i < list.size(); i++) {
        ReviewDTO dto = list.get(i);
       
    	Long postId = dto.getPostId();
    	String postBoard  = dto.getPostBoard();
    	 String userId = dto.getUserId();
    	 String postTitle = dto.getPostTitle();
    	 String postDate = dto.getPostDate();
    	 String postText = dto.getPostText();
    	 Long contId = dto.getContId();
%>
        <tr>
            <td><%= postId %></td>
            <td><%= postBoard %></td>
            <td><%= userId %></td>
            <td><%= postTitle %></td>
            <td><%= postDate %></td>
            <td><%= postText %></td>
            <td><%= contId %></td>
            <td>
                 <input type="button" value="삭제" class="delBtn" data-xxx="<%=postId%>">
            </td>
        </tr>
<%
   }
%>

</table>

<!-- 스타일 추가 -->
<style>
    .delBtn {
        background-color: #ff6961; /* 적절한 색상 선택 */
        color: white;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
    }
</style>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".delBtn").on("click", function() {
            var postId = $(this).attr("data-xxx");
            location.href = "ReviewDelServlet?postId=" + postId;
        });
    });
</script>
 