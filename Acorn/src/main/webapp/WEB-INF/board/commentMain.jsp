<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.dto.CommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.Map" %>    
<%@page import="java.util.HashMap" %>  
<%@page import="com.dto.MemberDTO" %>  
<%

MemberDTO dto =  (MemberDTO)session.getAttribute("loginUser");

//session scoep에 저장된 userid
String userId = null;
if(dto != null){
	userId = dto.getUserId();
}

//session scope에 저장된 nickname
String nickname = null;
if(dto != null){
	nickname = dto.getNickname();
}
%>
<script type="text/javascript">

	window.onload = init;
	var flag = false;

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
				postId : $("#postidComment").val(),
				userId : $.trim($("#useridComment").val()),
				comDate : $("#comdate").text(),
				comText : $("#comtext").val(),
				nickname : $.trim($("#nickname").text())
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
		
	 var comId = $(this).attr("id");
 		console.log("deleteCommentBtn 이벤트의",comId);
 	 
 	 
 	/*  만약 DB에서도 삭제할 거라면 이거 활성화
	 $.ajax({
			
			type: "post",
			url: "/Acorn/CommetDeleteServlet",
			data:{
				//댓글 삭제를 위해서 서버로 comid를 넘겨주고 있음
				comId : $(this).attr("id")
			},
			dataType: "text",
			success :  function(data, status, xhr){
				console.log(data);
			
				console.log(data);
				
				if(data == "댓글이 삭제되었습니다."){
				alert(data);
				CommentSelectAllServlet(); 
				 
				}else{
					alert("댓글을 삭제할 수 없습니다.");
				}
			}
			
		})//delete ajax  //만약 DB에서 삭제할 거라면 이거 활성화 */
		
		
		//[]★만약 DB에서 안 지우고 삭제된 내용이라고 내용만 변경할거면 이거 활성화] 
		//update인데 고객입장에선 delete이고, DB엔 내용만 Update되고 레코드는 살아있음
		$.ajax({
			
			type: "post",
			url: "/Acorn/CommentUpdateServlet",
			data:{
				//댓글 수정(삭제)를 위해서 서버로 comid를 넘겨주고 있음
				comId : $(this).attr("id"),
				How : "0"
				//아래 수정버튼 진행 시 똑같이 CommentUpdateServlet로 넘어가기 때문에 구분짓기 위해서 HOW라는 KEY=VALUE를 이용함
				//0이면 delete(update) 이고 1이면 찐update임
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
		
		
	});//deleteCommentBtn doc end
	
	
	
	$(document).on("click",".updateCommentBtn",function(){	
		//수정 버튼 눌러서 내용 수정할 수 있게 화면이 바뀌는 기능
		
		 var comId = $(this).attr("data-xxx");
	 	console.log("updateCommentBtn 이벤트의",comId);
	 		
	 	$("#comText"+comId).removeAttr("style");
	 	$("#comText"+comId).removeAttr("readonly");
	 	//input태그 그냥 text처럼 보이게 하기 위해 줬던 속성 수거하는 중 (수정하려는 val값 수정할 수 있게\)
	 	
	 	$("#span"+comId).html("<button class='updateCommentBtn2' data-xxx='" + comId + "'>확인</button>");
	 	//실제로 update기능이 실행될 [확인] 버튼을 span태그 안에 넣어주는중
	 	
	})//updateCommentBtn doc end
	
	
	
	$(document).on("click",".updateCommentBtn2",function(){	
		//찐으로 update가 진행될 수 있는 [확인]이 실행되는 기능
		
		var comId = $(this).attr("data-xxx");
		
	$.ajax({
		
		type: "post",
		url: "/Acorn/CommentUpdateServlet",
		data:{
			//댓글 수정(삭제)를 위해서 서버로 comid를 넘겨주고 있음
			comId : $(this).attr("data-xxx"),
			comText : $("#comText"+comId).val(),
			How : "1"
			//위에 삭제버튼 진행 시 똑같이 CommentUpdateServlet로 넘어가기 때문에 구분짓기 위해서 HOW라는 KEY=VALUE를 이용함
			//0이면 delete(update) 이고 1이면 찐update임
		},
		dataType: "text",
		success :  function(data, status, xhr){
			console.log(data);
		
			if(data == "댓글이 수정되었습니다."){
			alert(data);
			$("#span"+comId).html(""); //[확인]버튼 다시 없애버리는중
			CommentSelectAllServlet(); 
			 
			}else{
				alert("댓글을 수정할 수 없습니다.");
			}
			
		},
		error : function(xhr, status, error){
			console.log("에러코드 add"+status);
		}
		
	})//AJAX END
		
	})//updateCommentBtn2 END


	
	
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
				            var comId = data.commentDBList[i].comId;
				            var userId = data.commentDBList[i].userId;
				            var comDate = data.commentDBList[i].comDate;
				            var comText = data.commentDBList[i].comText;
				            var nickname = data.commentDBList[i].nickname;
				            console.log("commentseletall에서   ",comId, " ", userId, " ", comDate," ",nickname);
				             
				            // 각 댓글 항목
				            mesg += "<li class='comment-item'>";
				            mesg += "<div class='comment-meta'>";
				            mesg += "<strong>" + nickname + "</strong> | <span>" + comDate + "</span>";
				            mesg += "</div>";
				            mesg += "<input type='text' class='comment-content' style='border:none;outline:none' id='comText"+comId+"' readonly value='"+comText+"'>";
				            if(userId=="<%=userId%>"){
				            	mesg += "<div class='comment-actions'>";
					        	mesg += "<button id='" + comId + "' class='btn btn-danger btn-sm btn-spacing deleteCommentBtn' data-xxx='" + i + "'>삭제</button>";
					        	mesg += "<button id='update" + comId + "' class='updateCommentBtn' data-xxx='" + comId + "'>수정</button>";
					        	mesg += "<span id='span" + comId + "'></span>" //수정버튼 눌렀을 때 이 자리에 [확인] 버튼이 오게 될 것임
					        	mesg += "</div>";
				            }

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
	 <input type="hidden" id="useridComment" name="userid" value="<%=userId%>" > <!--DB저장용 userid -->

<div id="CommetList" style="margin-top: 20px;">
  	
  	<!--작성된 댓글 목록 보기-->
  	
</div> 
<div class="card mt-4" >
            <div class="card-header">댓글 작성</div>
            <div class="card-body">
                <span id="nickname" name="nickname">
                    <% if (userId == null) { %>
                        로그인을 해주세요.
                    <% } else { %>
                        <%= nickname %>
                    <% } %>
                </span>
                <br>
                <textarea id="comtext" class="form-control" name="comtext" style="height: 100px;" placeholder="댓글을 입력하세요. 지나친 욕설/비방 작성 시 사이트 이용에 제재를 받을 수 있습니다." <% if(userId == null) { %>disabled<% } %> ></textarea>
                <div style="text-align: right">
                    <input type="button" id="sendButton" value="등록" class="btn btn-action btn-spacing" style="margin-top: 10px;">
                </div>
            </div>
</div>
