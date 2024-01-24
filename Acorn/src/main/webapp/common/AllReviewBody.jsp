<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//데이터 받아오는 곳
	//글쓴이 정보, 내용 등등
%>
<div class="container-fluid pt-3" align="center">
        <table>
            <tr>
                <!-- start for -->
                <%for(int i=1; i<=25; i++){ %>
                <td>
                    <table style="width: 320px; height: 240px;">
                        <tr>
                            <td>
                            	<div class="border rounded-2" style="color:white;">
                            		<div class="border-bottom" style="height: 25px; ">글쓴이 정보</div>
                            		<div style="height: 200px;">내용</div>
                            		<div class="border-top " style="height: 25px;">좋아요</div>
                            	</div>
                            </td>
                        </tr> 
                    </table>
                </td>
                <!-- if(i%4==0){ -->
                <%if(i%4==0){ %>
                <tr>
                    <td style="height: 10px;"></td>
                </tr>
                <!-- }end if -->
                <!-- end for -->
                <%}} %>
            </tr>
        </table>
</div>