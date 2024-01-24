<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터 받아오는 곳 사진이랑 아이디만 있으면 될 것 같아요
%>
<div class="container-fluid pt-5 " align="center" style="background-color:transparent;">
        <table>
            <tr>
                <!-- start for -->
                <%for(int i=1; i<=33; i++){ %>
                <td>
                    <table style="background-color:transparent">
                        <tr>
                            <td>
                            	<div class=" align-self-center p-0" style="background-color:transparent;">
                					<a href="MoveToContentDetailServlet?movie=1"><img src="image/movie1.jpg" width="270px" height="330px"></a>
            					</div>
                            </td>
                        </tr> 
                    </table>
                </td>
                <!-- if(i%5==0){ -->
                <%if(i%5==0){ %>
                <tr>
                    <td style="height: 10px;"></td>
                </tr>
                <!-- }end if -->
                <!-- end for -->
                <%}} %>
            </tr>
        </table>
</div>