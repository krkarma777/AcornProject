<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.CommentDTO"%>
<%@ page import="java.util.List"%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<%
    List<CommentDTO> list = (List<CommentDTO>) request.getAttribute("commentList");
%>

<br>
<br>
<div style="text-align: center;">
    <h1> 내 댓글</h1>
    <br>
    <br>
    <table class="table table-striped" border="1">
        <tr>
            <th>Comment ID</th>
            <th>Post ID</th>
            <th>User ID</th>
            <th>Comment Date</th>
            <th>Comment Text</th>
            <th>Above Comment</th>
            <th>Delete</th>
        </tr>

        <%
            for (int i = 0; i < list.size(); i++) {
                CommentDTO dto = list.get(i);
                int comid = dto.getComid();
                Long postid = dto.getPostid();
                String comdate = dto.getComdate();
                String userId = dto.getUserId();
                String comtext = dto.getComtext();
                int abovecom = dto.getAbovecom();
        %>

        <tr>
            <td><%= comid %></td>
            <td><%= postid %></td>
            <td><%= userId %></td>
            <td><%= comdate %></td>
            <td><%= comtext %></td>
            <td><%= abovecom %></td>
            <td>
                <input type="button" value="삭제" class="delBtn" data-xxx="<%=comid%>">
            </td>
        </tr>

        <%
            }
        %>
    </table>
</div>

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
            var comid = $(this).attr("data-xxx");
            location.href = "CommentDelServlet?comid=" + comid;
        });
    });
</script>

