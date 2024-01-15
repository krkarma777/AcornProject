<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.domain.dto.PostDTO" %>
<!DOCTYPE html>
<html>
<head>
    <%
        // 글쓴 유저 id 실제 db에서 뽑아올 예정 
        Long userid = 1L;
        
        // 수정할 글의 정보를 받아옵니다.
        PostDTO post = (PostDTO) request.getAttribute("post");
    %>
    <meta charset="UTF-8">
    <title>글 수정 - <%= post.getPostTitle() %></title>
    
    <!-- Bootswatch Sketchy 테마 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sketchy/bootstrap.min.css" integrity="sha384-RxqHG2ilm4r6aFRpGmBbGTjsqwfqHOKy1ArsMhHusnRO47jcGqpIQqlQK/kmGy9R" crossorigin="anonymous">
    <!-- TinyMCE script -->
    <script src="https://cdn.tiny.cloud/1/ok3w2lvptkyfth3qjjks4fv0f99459nvfx76ire0ttwxcrij/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
            
    <style>
        /* 제목 입력란에 스타일을 적용하는 CSS 코드 */
        #title {
            width: 300px; /* 원하는 너비로 조정 */
            padding: 5px;
            font-size: 16px; /* 원하는 글자 크기로 조정 */
        }

        /* 입력 전에 텍스트를 흐릿하게 표시하는 스타일 */
        .placeholder {
            color: #999;
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
            margin-top: 10px; /* 버튼과 TinyMCE 에디터 사이 간격 조정 */
        }

        /* 반응형 그리드 시스템 */
        @media (min-width: 576px) {
            .container {
                max-width: 540px; /* 작은 화면에 대한 최대 너비 조정 */
            }
        }

        @media (min-width: 768px) {
            .container {
                max-width: 720px; /* 중간 크기 화면에 대한 최대 너비 조정 */
            }
        }

        @media (min-width: 992px) {
            .container {
                max-width: 960px; /* 대형 화면에 대한 최대 너비 조정 */
            }
        }

        @media (min-width: 1200px) {
            .container {
                max-width: 1140px; /* 매우 큰 화면에 대한 최대 너비 조정 */
            }
        }
        
        @font-face {
            font-family: 'ChosunGu';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff') format('woff');
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
        
        .navbar {
		    position: fixed; /* 추가: 고정 위치 설정 */
		    width: 100%; /* 추가: 화면 전체 너비로 설정 */
		    z-index: 2; /* 추가: 에디터보다 위에 나타나도록 설정 */
		        top: 0; /* 수정: 브라우저 상단에 고정 */
		}
    </style>
    <script>
        // 입력란에 포커스가 이동하면 흐릿한 텍스트를 숨기는 함수
        function hidePlaceholder() {
            var titleInput = document.getElementById('title');
            if (titleInput.value === '제목을 입력해주세요') {
                titleInput.value = '';
                titleInput.classList.remove('placeholder');
            }
        }

        // 입력란에서 포커스가 빠져나가면 텍스트를 다시 표시하는 함수
        function showPlaceholder() {
            var titleInput = document.getElementById('title');
            if (titleInput.value === '') {
                titleInput.value = '제목을 입력해주세요';
                titleInput.classList.add('placeholder');
            }
        }
        
        // form 요소에서 submit 이벤트가 발생할 때 호출되는 함수
        function validateForm() {
            // 제목과 내용을 가져옴
            var titleInput = document.getElementById('title');
            var contentInput = document.getElementsByName('content')[0];

            // 제목과 내용이 비어있는지 확인
            if (titleInput.value.trim() === '' || contentInput.value.trim() === '') {
                // 비어있을 경우 경고 메시지를 표시하고 submit을 중지
                alert('제목과 내용을 모두 입력하세요.');
                return false;
            }

            // 검증 통과 시 폼을 제출
            return true;
        }

        tinymce.init({
            selector: 'textarea',
            // 한국어로 설정, 헤더에서 사용자 국가 받아와서 변경 기능 추가 가능	
            language: 'ko_KR',
            // 사용할 플러그인
            plugins: 'mentions anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage tableofcontents mergetags powerpaste autocorrect a11ychecker typography inlinecss',
            // 에디터 툴바 설정
            toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat | fileupload',
            file_picker_types: 'file image media',
            file_picker_callback: function (callback, value, meta) {
                // 파일 및 이미지를 업로드하는 로직을 추가
                // 적절한 업로드 대화 상자 또는 서버와의 통신을 구현해야 함
                // 로드된 파일의 URL을 얻어와서 에디터에 반환해야 함. 
                // 서버에서 업로드된 파일을 저장하고 해당 파일의 URL을 반환하는 엔드포인트를 구현해야 함      
            },
            branding: false,
        });
    </script>
</head>
<body>
<%@ include file="/WEB-INF/navBar.jsp"%>
<div class="container mt-5 editor-wrapper">
    <form method="post" onsubmit="return validateForm();">
		<div class="mb-3">
		    <input type="text" name="title" id="title" class="form-control placeholder"
		           onfocus="hidePlaceholder()" onblur="showPlaceholder()">
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
            <button type="submit" class="btn btn-primary submit-button">수정 완료</button>
        </div>
    </form>
</div>

<!-- Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
