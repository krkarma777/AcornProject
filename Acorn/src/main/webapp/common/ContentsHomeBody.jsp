<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터 뽑아 오는 곳: 기준은 아직이지만 최근 상영 중에 인기순이 가장 좋지 않을까?
	// 상위 10개 정도를 뽑아서 아래 for문 부분에 돌리기.
%>    

<div class="  " style="" align="center">
	<div id="carouselExampleControls" class="carousel slide justify-content-center pt-5" data-bs-interval="false" style="width:1390px; height: 500px;">
		<div class="carousel-inner">
			<!-- for문 시작 -->
    		<div class="carousel-item active">
           		<div class="d-block" align="center">
 					<div class="d-flex">
						<div class="me-1 bg-danger align-self-center">
							<a href="MoveToContentDetailServlet?movie=1"><img src="image/웡카.jpg" width="270px" height="382px"></a>
						</div><!-- <a>누르면 MoveToContentDetailServlet으로 이동 -->
						<div class="mx-1 bg-light align-self-center">
							<a href="#"><img src="image/시민덕희.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="mx-1 bg-primary align-self-center">
        					<a href="#"><img src="image/도그맨.jpg" width="270px" height="382px"></a>
						</div>
        				<div class="mx-1 bg-dark align-self-center">
        					<a href="#"><img src="image/너의이름은.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="ms-1 bg-info align-self-center">
        					<a href="#"><img src="image/라라랜드.jpg" width="270px" height="382px"></a>
						</div>
						<!-- if문 5개 되면 새로운 carousel-item을 만들기 -->
					</div>
				</div>
			</div>
			<!-- for문 끝 -->
			
			<div class="carousel-item">
           		<div class="d-block"  align="center">
 					<div class="d-flex">
						<div class="mx-1 bg-danger align-self-center">
							<a href="MoveToContentDetailServlet?movie=1"><img src="image/라라랜드.jpg" width="270px" height="382px"></a>
						</div><!-- <a>누르면 MoveToContentDetailServlet으로 이동 -->
						<div class="mx-1 bg-light align-self-center">
							<a href="#"><img src="image/웡카.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="mx-1 bg-primary align-self-center">
        					<a href="#"><img src="image/시민덕희.jpg" width="270px" height="382px"></a>
						</div>
        				<div class="mx-1 bg-dark align-self-center">
        					<a href="#"><img src="image/도그맨.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="mx-1 bg-info align-self-center">
        					<a href="#"><img src="image/너의이름은.jpg" width="270px" height="382px"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev" style="width: 100px;">
        	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
          	<span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next" style="width: 130px">
          	<span class="carousel-control-next-icon" aria-hidden="false"></span>
         	<span class="visually-hidden">Next</span>
        </button>
	</div>
	
	

	<div class="d-flex justify-content-center px-5" style="height: 300px">
		<div class="mx-1 bg-secondary align-self-center">
			<a href="#">정보게시판</a>
		</div>
        <div class="mx-1 bg-light align-self-center">
        	<a href="#">자유게시판</a>
		</div>
        <div class="mx-1 bg-primary align-self-center">
        	모임게시판
		</div>
	</div>
	
</div>