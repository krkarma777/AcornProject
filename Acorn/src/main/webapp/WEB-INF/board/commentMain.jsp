<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.dto.CommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.Map" %>    
<%@page import="java.util.HashMap" %>    
<%
//session scoep에 저장된 userid
String userid =  "zz";
%>
<script type="text/javascript">

	window.onload = init;

	function init(){
		function clock() { //작성시간을 실시간으로 보여주기 위한 함수
		    var time = new Date();
		    
		    var year = time.getYear();
		    var month = time.getMonth();
		    var date = time.getDate();
		    var day = time.getDay();
		    var hours = time.getHours();
		    var minutes = time.getMinutes();
		    var seconds = time.getSeconds();
		
		    $("#comdate").text((year-100)+"년 "+(month+1)+"월 "+date+"일 "+(hours<10?"0"+hours : hours)+":"+(minutes<10?"0"+minutes : minutes)+":"+(seconds<10?"0"+seconds : seconds));
		        
		}
		clock();
		setInterval(clock, 1000); // 1초마다 실행
	
	}//init
	
	
	$(document).ready(function(){
		
		
		// 페이지 로딩 시 댓글 목록을 불러옴
	    CommentSelectAllServlet();
		
	$("#sendButton").click(function(){
		

		$.ajax({
			
			type: "post",
			url: "/Acorn/CommetInsertServlet",
			data:{
				postid : $("#postidComment").val(),
				userid : $.trim($("#userid").text()),
				comdate : $("#comdate").text(),
				comtext : $("#comtext").val()
			},
			success :  function (data, status, xhr){
				
				CommentSelectAllServlet();
				
			},
			error : function(xhr, status, error){
					
				$("#CommetList").text(error);
				$("#CommetList").text(status);
				
			}
			
		})//ajax

 	});//sendButton end

}); //doc end1


$(document).on("click",".deleteCommentBtn",function(){
		
	 var comid = $(this).attr("id");
 		console.log("버튼클릭이벤트의",comid);
 	 
 	 
 	/*  만약 DB에서도 삭제할 거라면 이거 활성화
	 $.ajax({
			
			type: "post",
			url: "/Acorn/CommetDeleteServlet",
			data:{
				//댓글 삭제를 위해서 서버로 comid를 넘겨주고 있음
				comid : $(this).attr("id")
			},
			dataType: "text",
			success :  function(data, status, xhr){
				console.log(data);
			
				if(data == "삭제된 댓글입니다."){
				$('#'+ comid ).html("<i>"+data+"</i>");//작성된 댓글의 textarea를 span태그로 묶고, span에 id를 comid로 줬었음. 그걸 이용해서 내용 바꾸는중
				CommentSelectAllServlet(); 
				 
				}else{
					alert("댓글을 삭제할 수 없습니다.");
				}
				
			},
			error : function(xhr, status, error){
				console.log("에러코드 add"+status);
			}
			
		})//delete ajax  //만약 DB에서 삭제할 거라면 이거 활성화 */
		
		
		//]★만약 DB에서 안 지우고 삭제된 내용이라고 내용만 변경할거면 이거 활성화] 
		//update인데 고객입장에선 delete이고, DB엔 내용만 Update되고 레코드는 살아있음
		$.ajax({
			
			type: "post",
			url: "/Acorn/CommentUpdateServlet",
			data:{
				//댓글 수정(삭제)를 위해서 서버로 comid를 넘겨주고 있음
				comid : $(this).attr("id")
			},
			dataType: "text",
			success :  function(data, status, xhr){
				console.log(data);
			
				if(data == "댓글이 삭제되었습니다."){
				alert(data);
				CommentSelectAllServlet(); 
				 
				}else{
					alert("댓글을 삭제할 수 없습니다.");
				}
				
			},
			error : function(xhr, status, error){
				console.log("에러코드 add"+status);
			}
			
		})//update(고객입장에선 delete, DB에선 안 지워짐) ajax
		
		
	});//doc 2
	
	
	
	function CommentSelectAllServlet() {
		//console.log("CommentSelectAllServlet");
		
		 var postId = <%= request.getParameter("postId")%>
		 
		 $.ajax({
				
				type: "post",
				url: "/Acorn/CommentSelectAllServlet",
		        data: {
		            postId: postId // postid 값을 요청에 포함시킴
		        },
				dataType: "json",
				success :  function(data, status, xhr){
					var mesg ="";
					var length = data.commentDBList.length;
					// 댓글이 없는 경우
				    if(length == 0) {
				        mesg = "<div class='text-center'>댓글이 없습니다.</div>";
				    } else {
				        mesg = "<ul class='comment-list'>"; // 댓글 리스트 시작
				        for(var i = 0; i < length; i++){
				            var comid = data.commentDBList[i].comid;
				            var userid = data.commentDBList[i].userid;
				            var comdate = data.commentDBList[i].comdate;
				            var comtext = data.commentDBList[i].comtext;
				            console.log(comid, " ", userid, " ", comdate);
				            
				            // 각 댓글 항목
				            mesg += "<li class='comment-item'>";
				            mesg += "<div class='comment-meta'>";
				            mesg += "<strong>" + userid + "</strong> | <span>" + comdate + "</span>";
				            mesg += "</div>";
				            mesg += "<p class='comment-content'>" + comtext + "</p>";
				            mesg += "<div class='comment-actions'>";
				            mesg += "<button id='" + comid + "' class='btn btn-danger btn-sm btn-spacing deleteCommentBtn' data-xxx='" + i + "'>삭제</button>";
				            mesg += "</div>";
				            mesg += "</li>";
				        }
				        mesg += "</ul>"; // 댓글 리스트 종료
				    }
					$("#CommetList").html(mesg); //아래 출력하기
					
				},
				error : function(xhr, status, error){
					console.log("에러코드 selectAll"+status);
				}
				
			})//selectAll ajax
			
			//댓글 입력 후 텍스트 칸 비워주는 코드
			$("#comtext").val("");
		
	}

</script>	

	 <input type="hidden" id="postidComment" name="postid" value=<%=request.getParameter("postId")%> > <!-- 굳이 고객한테 보일 필요가 없으니 hidden 태그 -->

<div id="CommetList" style="margin-top: 20px;">
  	
  	<!--작성된 댓글 목록 보기-->
  	
</div> 
<div class="card mt-4" >
            <div class="card-header">댓글 작성</div>
            <div class="card-body">
                <span id="userid" name="userid">
                    <% if (userid == null) { %>
                        로그인을 해주세요.
                    <% } else { %>
                        <%= userid %>
                    <% } %>
                </span>
                <br>
                <textarea id="comtext" class="form-control" name="comtext" style="height: 100px;" placeholder="댓글을 입력하세요. 지나친 욕설/비방 작성 시 사이트 이용에 제재를 받을 수 있습니다." <% if(userid == null) { %>disabled<% } %> ></textarea>
                <div style="text-align: right">
                    <input type="button" id="sendButton" value="등록" class="btn btn-action btn-spacing" style="margin-top: 10px;">
                </div>
            </div>
</div>
