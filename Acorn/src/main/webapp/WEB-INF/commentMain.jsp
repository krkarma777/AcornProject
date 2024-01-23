<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.dto.CommentDTO"%>
<%@page import="java.lang.reflect.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.Map" %>    
<%@page import="java.util.HashMap" %>    
<%
//session scoep에 저장된 userid
String userid =  "zz";
//sesstion scope에 저장된 바로 지금 post id
Long postid = 1L;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
		
		
	$("#sendButton").click(function(){
		

		$.ajax({
			
			type: "post",
			url: "CommetInsertServlet",
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


	$(document).on("click","button",function(){
		
	 var comid = $(this).attr("id");
 		console.log("버튼클릭이벤트의",comid);
 	 
 	 
 	/*  만약 DB에서도 삭제할 거라면 이거 활성화
	 $.ajax({
			
			type: "post",
			url: "CommetDeleteServlet",
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
			url: "CommentUpdateServlet",
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
		
		
		 $.ajax({
				
				type: "post",
				url: "CommentSelectAllServlet",
				data:{ },
				dataType: "json",
				success :  function(data, status, xhr){
					
					var mesg ="";
					
					for(var i = 0; i<data.commentDBList.length; i++){
						
						
						var comid = data.commentDBList[i].comid;
						var userid =  data.commentDBList[i].userid;
						var comdate = data.commentDBList[i].comdate;
						var comtext = data.commentDBList[i].comtext;
						console.log(comid,"      ",userid,"      ",comdate)
				
						mesg += "<b>작성자 </b>"+userid+"<br>"
						mesg += "<b>작성시간 </b>"+comdate+"<br>"
						mesg += "<span id="+comid+">"+comtext+"</span><br>"
						mesg += "<button id="+comid+" data-xxx="+i+">삭제</button>"
						//data-xxx=comid_json.comid로 이 태그를 특정하고 있음. comid_json.comid는 DB 뒤져서 순차적으로 가져온거라 생기는 댓글마다 다 다름
						mesg += "<hr>"
						
					}//for문종료
					
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
<title>Insert title here</title>
</head>
<body>
	 <input type="hidden" id="postidComment" name="postid" value=<%=postid %> > <!-- 굳이 고객한테 보일 필요가 없으니 hidden 태그 -->
   	 <b>작성자 </b><span id="userid" name="userid">
   	 <% /* 로그인 정보가 없으면 "로그인을 해주세요" 가 뜨고, 정보가 있으면 id가 출력 됨 */
   	 
   	 if( userid == null){%>
   	  
   	  로그인을 해주세요.
   	  
   	  <%}else{ %>
   	  
   	  <%= userid %>
   	 
   	 <%} %>
   	 </span>  <!--작성자 자동으로  등록되게 하고 싶음-->
     <b>작성시간 </b><span id="comdate" name="comdate"> </span>  <!--당일 날짜가 자동으로 등록되게 하고 싶음-->
     <br>
     <hr>
      <!--처음 댓글 입력하는 창-->
     <textarea rows="3" cols= "84" id="comtext" name="comtext" style="height: 50px;" placeholder="댓글을 입력하세요. 지나친 욕설/비방 작성 시 사이트 이용에 제재를 받을 수 있습니다." <%if( userid == null){%>disabled<%} %> ></textarea>
   	 <input type="button" id="sendButton" value="작성">

  	<div id="CommetList">
  	
  	<!--작성된 댓글 목록 보기-->
  	
  	</div> 
</body>
</html>