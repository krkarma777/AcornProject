<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문밤</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <style type="text/css">
	body{
		background-image: url(image/starryNight.jpg);
  		background-size: cover;
	}
	</style>
</head>
<body>
<jsp:include page="//common/navbar.jsp"></jsp:include>
<div class="container-fluid bg-light text-danger">
        <table>
            <tr>
                <!-- start for -->
                
                <td>
                    <table border="1" style="width: 200px; height: 220px;">
                        <tr style="width: 200px; height: 20px;">
                            <td>글쓴이 정보</td>
                        </tr>
                        <tr style="width: 200px; height: 200px;">
                            <td>글 내용</td>
                        </tr>
                    </table>
                </td>
                <!-- if(i%4==0){ -->
                <tr>
                    <td style="height: 10px;"></td>
                </tr>
                <!-- }end if -->
                <!-- end for -->
            </tr>
        </table>
    </div>
</body>
</html>