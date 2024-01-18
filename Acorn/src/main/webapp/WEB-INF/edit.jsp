<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dto.PostDTO"%>
<!DOCTYPE html>
<html>
<head>
<%
// 글쓴 유저 id 실제 db에서 뽑아올 예정 
String userid = "zz";

// 수정할 글의 정보를 받아옵니다.
PostDTO post = (PostDTO) request.getAttribute("post");
%>
<meta charset="UTF-8">
<title>글 수정 - <%=post.getPostTitle()%></title>

<!-- Bootstrap JS (optional) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


<!-- TinyMCE script -->
<script
	src="https://cdn.tiny.cloud/1/ok3w2lvptkyfth3qjjks4fv0f99459nvfx76ire0ttwxcrij/tinymce/6/tinymce.min.js"
	referrerpolicy="origin"></script>

<style>
/* 제목 입력란에 스타일을 적용하는 CSS 코드 */
#title {
	width: 100%;
	padding: 5px;
	font-size: 16px;
	border: 1px solid #ced4da; /* 제목 입력창의 테두리 스타일 추가 */
}

body {
	font-family: 'ChosunGu';
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
}
/* 수정된 스타일 */
.container {
	margin-top: 50px;
}

.submit-button {
	margin-top: 5px; /* 버튼과 TinyMCE 에디터 사이 간격 조정 */
	margin-right: 12px; /* 버튼을 왼쪽으로 12px 이동 */
	width: auto; /* 버튼의 너비를 자동으로 조절하도록 설정 */
}

/* 반응형 그리드 시스템 */
@media ( min-width : 576px) {
	.container {
		max-width: 540px; /* 작은 화면에 대한 최대 너비 조정 */
	}
}

@media ( min-width : 768px) {
	.container {
		max-width: 720px; /* 중간 크기 화면에 대한 최대 너비 조정 */
	}
}

@media ( min-width : 992px) {
	.container {
		max-width: 960px; /* 대형 화면에 대한 최대 너비 조정 */
	}
}

@media ( min-width : 1200px) {
	.container {
		max-width: 1140px; /* 매우 큰 화면에 대한 최대 너비 조정 */
	}
}

@font-face {
	font-family: 'ChosunGu';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

/* 추가된 스타일 */
.row {
	display: flex;
	justify-content: flex-end; /* 버튼을 오른쪽으로 정렬 */
}

.submit-button {
	margin-left: 10px; /* 버튼과 에디터 간격 조정 */
}

/* 에디터를 감싸는 스타일 추가 */
.editor-wrapper {
	border: 1px solid #ddd; /* 네모난 선 스타일 추가 */
	padding: 20px; /* 내부 여백 추가 */
	margin-top: 20px; /* 상단 여백 추가 */
	position: relative; /* 추가: 상대적 위치 설정 */
	z-index: 1; /* 추가: 네비게이션 바보다 위에 나타나도록 설정 */
}

.mb-3 {
	margin-bottom: 20px; /* 제목 입력란과 에디터 간격을 조절하는 부분 */
}

#title {
	width: 100%; /* 폭을 100%로 설정하여 동일하게 조절 */
	padding: 5px;
	font-size: 16px;
}
/* 네비게이션바 위치 설정 */
.navbar {
	position: fixed; /* 추가: 고정 위치 설정 */
	width: 100%; /* 추가: 화면 전체 너비로 설정 */
	z-index: 1030; /* 추가: 에디터보다 위에 나타나도록 설정 */
	top: 0; /* 수정: 브라우저 상단에 고정 */
}
/* 검색창 너비 조절 */
.searchInput {
	width: 70vh;
}
</style>
<script>
	// 파일을 업로드하는 함수
	function uploadFile() {
		var formData = new FormData($('form')[0]);

		$.ajax({
			url : '/upload', // 서버 측 엔드포인트: 파일 처리
			type : 'POST',
			data : formData, // 전송할 데이터 (FormData 객체)
			processData : false, // 데이터 처리 방지
			contentType : false, // 컨텐츠 타입 지정을 jQuery에게 위임
			cache : false, // 캐시 비활성화
			timeout : 600000, // 타임아웃 설정 (10분)
			enctype : 'multipart/form-data', // 파일 업로드를 위한 인코딩 방식
			success : function(response) {
				// 파일 업로드 성공 시 동작
				console.log(response);
			},
			error : function(error) {
				// 파일 업로드 실패 시 동작
				console.error(error);
			}
		});
	}

	// jQuery를 사용한 입력란 이벤트 처리
	$(document).ready(function() {

		// 폼 제출 시 validateForm 함수 호출
		$('form').submit(function(event) {
			validateForm(event);
		});
	});

	// form 요소에서 submit 이벤트가 발생할 때 호출되는 함수
	function validateForm(event) {
		// 제목과 내용을 가져옴
		var titleInput = $('#title');
		var contentInput = $('textarea[name="content"]');

		// 제목과 내용이 비어있는지 확인
		if (titleInput.val().trim() === '제목을 입력해주세요'
				|| contentInput.val().trim() === '') {
			// 비어있을 경우 경고 메시지를 표시하고 submit을 중지
			alert('제목과 내용을 모두 입력하세요.');
			event.preventDefault();
		}

	}

	tinymce
			.init({
				selector : 'textarea',
				// 한국어로 설정, 헤더에서 사용자 국가 받아와서 변경 기능 추가 가능    
				language : 'ko_KR',
				// 사용할 플러그인
				plugins : 'mentions anchor autolink charmap codesample emoticons image link lists media searchreplace visualblocks checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage powerpaste autocorrect a11ychecker typography inlinecss',
				// 에디터 툴바 설정
				toolbar : 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media  mergetags | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat | fileupload',
				file_picker_types : 'file image media',
				// 이미지, 동영상 업로드를 위한 콜백 설정
				file_picker_callback : function(callback, value, meta) {
					var input = document.createElement('input');
					input.setAttribute('type', 'file');

					if (meta.filetype === 'image') {
						input.setAttribute('accept', 'image/*');
					} else if (meta.filetype === 'video') {
						input.setAttribute('accept', 'video/*');
					}

					input.onchange = function() {
						var file = this.files[0];
						var formData = new FormData();
						formData.append('fileUpload', file);

						$.ajax({
							url : '/Acorn/upload', // 서버 측 파일 업로드 핸들러
							type : 'POST',
							data : formData,
							processData : false,
							contentType : false,
							success : function(response) {
								// 서버가 반환하는 파일 URL을 사용하여 에디터에 이미지 삽입
								callback(response.fileUrl);
							},
							error : function() {
								// 에러 처리
								alert('파일 업로드에 실패했습니다.');
							}
						});
					};

					input.click();
				},

				branding : false
			});
</script>
</head>
<body>

	<!-- 네비게이션바 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<div class="container-fluid">
			<!-- 로고 -->
			<a class="navbar-brand" href="#">로고</a>

			<!-- 토글 버튼 -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- 네비게이션 항목 -->
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mx-auto">
					<!-- 검색 바 -->
					<form class="d-flex w-100">
						<input class="form-control me-2 searchInput" type="search"
							placeholder="검색" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">검색</button>
					</form>
				</ul>
				<ul class="navbar-nav">
					<!-- 로그인, 마이페이지, 회원가입 버튼 -->
					<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
				</ul>
			</div>
		</div>
	</nav>



	<div class="container mt-5 editor-wrapper">
		<form method="post" onsubmit="return validateForm();">
			<div class="mb-3">
				<input type="text" name="title" id="title" class="form-control">
			</div>

			<script>
		    // JavaScript로 서버에서 받아온 제목을 설정
		    document.getElementById('title').value = '<%= post.getPostTitle() %>';
		</script>

			<input type="hidden" name="userid" id="userid" value="<%= userid %>">

			<div class="mb-3">
				<textarea name="content" class="form-control"><%= post.getPostText() %></textarea>
			</div>

			<div class="row">
				<button type="submit" class="btn btn-primary submit-button">수정
					완료</button>
			</div>
		</form>
	</div>

</body>
</html>
