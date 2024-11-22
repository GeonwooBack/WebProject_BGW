<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// 세션에서 로그인 상태 확인
String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="author" content="templatemo">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<title>SnapX Photography by TemplateMo</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Additional CSS Files -->
<link rel="stylesheet" href="assets/css/fontawesome.css">
<link rel="stylesheet"
	href="assets/css/templatemo-snapx-photography.css">
<link rel="stylesheet" href="assets/css/owl.css">
<link rel="stylesheet" href="assets/css/animate.css">
<link rel="stylesheet"
	href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
<style>
    /* 버튼 기본 스타일 */
    .btn {
        color: white !important; /* 텍스트 색상 */
        background-color: #007bff !important; /* 버튼 배경색 */
        border: none !important; /* 테두리 제거 */
        padding: 6px 12px; /* 버튼 내부 여백 */
        font-size: 13px; /* 텍스트 크기 */
        line-height: normal; /* 텍스트 라인 높이 */
        border-radius: 5px; /* 둥근 모서리 */
        cursor: pointer; /* 클릭 가능한 커서 */
        text-align: center; /* 텍스트 중앙 정렬 */
        white-space: nowrap; /* 텍스트 줄바꿈 방지 */
        min-width: 70px; /* 최소 너비 */
    }

    /* 호버 스타일 */
    .btn:hover {
        background-color: #0056b3; /* 호버 시 배경색 변경 */
        color: white; /* 호버 시 텍스트 색상 유지 */
    }

    /* 투명도 제거 */
    .btn {
        opacity: 1 !important; /* 투명도 제거 */
    }
</style>

</head>

<body>

	<!-- ***** Header Area Start ***** -->
	<header class="header-area header-sticky">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav class="main-nav">
						<a href="index.jsp" class="logo"> <img
							src="assets/images/logo.png" alt="SnapX Photography Template">
						</a>
						<ul class="nav">
							<li><a href="index.jsp" class="active">Home</a></li>
							<li class="has-sub"><a href="javascript:void(0)">Photos
									&amp; Videos</a>
								<ul class="sub-menu">
									<li><a href="contests.jsp">Contests</a></li>
									<li><a href="contest-details.jsp">Single Contest</a></li>
								</ul></li>
							<li><a href="categories.jsp">Categories</a></li>
							<li><a href="Board/ListPage.jsp">자유게시판</a></li>
						</ul>
						<div class="border-button">
							<%
							if (username != null) {
							%>
							<!-- 로그인 상태 -->
							<a href="mypage.jsp" class="btn btn-primary">마이페이지</a> <a
								href="LogoutServlet" class="btn btn-secondary">로그아웃</a>
							<%
							} else {
							%>
							<!-- 비로그인 상태 -->
							<button id="openLoginModal" class="btn btn-primary">로그인</button>
							<button id="openRegisterModal" class="btn btn-secondary">회원가입</button>
							<%
							}
							%>
						</div>
						<a class='menu-trigger'> <span>Menu</span>
						</a>
					</nav>
				</div>
			</div>
		</div>
	</header>

	<!-- Modal for Login -->
	<div id="loginModal" class="modal" style="display: none;">
		<div class="modal-content">
			<span class="close-modal" id="closeLoginModal">&times;</span>
			<h2>로그인</h2>
			<form action="LoginServlet" method="post">
				<label for="username">아이디:</label> <input type="text" id="username"
					name="username" required><br>
				<br> <label for="password">비밀번호:</label> <input type="password"
					id="password" name="password" required><br>
				<br>
				<button type="submit" class="btn btn-primary">로그인</button>
			</form>
		</div>
	</div>

	<!-- Modal for Register -->
	<div id="registerModal" class="modal" style="display: none;">
		<div class="modal-content">
			<span class="close-modal" id="closeRegisterModal">&times;</span>
			<h2>회원가입</h2>
			<form action="RegisterServlet" method="post"
				onsubmit="return handleRegisterSubmit();">
				<label for="newUsername">아이디:</label> <input type="text"
					id="newUsername" name="username" required><br>
				<br> <label for="newPassword">비밀번호:</label> <input
					type="password" id="newPassword" name="password" required><br>
				<br> <label for="name">이름:</label> <input type="text" id="name"
					name="name" required><br>
				<br> <label for="email">이메일:</label> <input type="email"
					id="email" name="email" required><br>
				<br> <label for="phoneNumber">전화번호:</label> <input type="text"
					id="phoneNumber" name="phoneNumber" required><br>
				<br> <label for="birthdate">생년월일:</label> <input type="date"
					id="birthdate" name="birthdate" required><br>
				<br> <label for="gender">성별:</label> <input type="radio"
					id="male" name="gender" value="male" required> <label
					for="male">남성</label> <input type="radio" id="female" name="gender"
					value="female" required> <label for="female">여성</label><br>
				<br>
				<button type="submit" class="btn btn-secondary">회원가입</button>
			</form>
		</div>
	</div>

	<!-- JavaScript -->
	<script>
    document.addEventListener("DOMContentLoaded", function () {
        // 모달 DOM 요소 가져오기
        const loginModal = document.getElementById('loginModal');
        const registerModal = document.getElementById('registerModal');
        const openLoginModalBtn = document.getElementById('openLoginModal');
        const openRegisterModalBtn = document.getElementById('openRegisterModal');
        const closeLoginModalBtn = document.getElementById('closeLoginModal');
        const closeRegisterModalBtn = document.getElementById('closeRegisterModal');

        if (openLoginModalBtn) {
            openLoginModalBtn.addEventListener('click', () => {
                loginModal.style.display = 'block';
            });
        }

        if (openRegisterModalBtn) {
            openRegisterModalBtn.addEventListener('click', () => {
                registerModal.style.display = 'block';
            });
        }

        if (closeLoginModalBtn) {
            closeLoginModalBtn.addEventListener('click', () => {
                loginModal.style.display = 'none';
            });
        }

        if (closeRegisterModalBtn) {
            closeRegisterModalBtn.addEventListener('click', () => {
                registerModal.style.display = 'none';
            });
        }

        window.addEventListener('click', (e) => {
            if (e.target === loginModal) loginModal.style.display = 'none';
            if (e.target === registerModal) registerModal.style.display = 'none';
        });
    });
</script>

</body>
</html>




<!-- ***** Main Banner Area Start ***** -->
<div class="main-banner">
	<div class="container">
		<div class="row">
			<div class="col-lg-10 offset-lg-1">
				<div class="header-text">
					<h2>
						Enter a world of <em>Photos</em> &amp; Amazing <em>Awards</em>
					</h2>
					<p>SnapX Photography is a professional website template with 5
						different HTML pages for maximum customizations. It is free for
						commercial usage. This Bootstrap v5.1.3 CSS layout is provided by
						TemplateMo Free CSS Templates.</p>
					<div class="buttons">
						<div class="big-border-button">
							<a href="contests.jsp">Explore SnapX Contest</a>
						</div>
						<div class="icon-button">
							<a href="https://youtube.com/templatemo" target="_blank"><i
								class="fa fa-play"></i> Watch Our Video Now</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ***** Main Banner Area End ***** -->


<section class="featured-items" id="featured-items">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="owl-features owl-carousel"
					style="position: relative; z-index: 5;">
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-01.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Walk In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i><i
											class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Vincent Adam</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $1.000 + Camera Nikon</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-02.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Smile In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Thomas Eddy</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $1,200 + Canon EOS R7</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-03.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Happy In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i><i
											class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Vincent Adam</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $1,800 + Canon EOS R6</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-01.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Walk In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Thomas Eddy</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $8,400 + Canon EOS R1</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-02.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Run In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i><i
											class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Vincent Adam</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $5,500 + Canon EOS R3</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-03.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Stay In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Thomas Eddy</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $4,400 + Canon EOS R5</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-01.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Walk In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Vincent Adam</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $3,800 + Canon EOS R6</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-02.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Shoot In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Vincent Adam</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $2,400 + Canon EOS R7</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="thumb">
							<img src="assets/images/featured-03.jpg" alt="">
							<div class="hover-effect">
								<div class="content">
									<h4>
										Fly In The Nature <i class="fa fa-star"></i><i
											class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span>
									</h4>
									<ul>
										<li><span>Contest Winner:</span> Vincent Adam</li>
										<li><span>Contest Author:</span> Anthony Soft</li>
										<li><span>Awards:</span> $1,200 + Canon EOS R10</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<section class="popular-categories">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6">
				<div class="section-heading">
					<h6>Our Categories</h6>
					<h4>
						Check Out <em>Popular</em> Contest <em>Categories</em>
					</h4>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="main-button">
					<a href="categories.jsp">Discover All Categories</a>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6">
				<div class="popular-item">
					<div class="top-content">
						<div class="icon">
							<img src="assets/images/icon-01.png" alt="">
						</div>
						<div class="right">
							<h4>Nature Pic Contest</h4>
							<span><em>126</em> Available Contests</span>
						</div>
					</div>
					<div class="thumb">
						<img src="assets/images/popular-01.png" alt=""> <span
							class="category">Top Contest</span> <span class="likes"><i
							class="fa fa-heart"></i> 256</span>
					</div>
					<div class="border-button">
						<a href="contest-details.jsp">Browse Nature Pic Contests</a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6">
				<div class="popular-item">
					<div class="top-content">
						<div class="icon">
							<img src="assets/images/icon-02.png" alt="">
						</div>
						<div class="right">
							<h4>Random Pic Contest</h4>
							<span><em>116</em> Available Contests</span>
						</div>
					</div>
					<div class="thumb">
						<img src="assets/images/popular-02.png" alt=""> <span
							class="category">Top Contest</span> <span class="likes"><i
							class="fa fa-heart"></i> 256</span>
					</div>
					<div class="border-button">
						<a href="contest-details.jsp">Browse Random Pic Contests</a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6">
				<div class="popular-item">
					<div class="top-content">
						<div class="icon">
							<img src="assets/images/icon-03.png" alt="">
						</div>
						<div class="right">
							<h4>Portrait Pic Contest</h4>
							<span><em>164</em> Available Contests</span>
						</div>
					</div>
					<div class="thumb">
						<img src="assets/images/popular-03.png" alt=""> <span
							class="category">Top Contest</span> <span class="likes"><i
							class="fa fa-heart"></i> 256</span>
					</div>
					<div class="border-button">
						<a href="contest-details.jsp">Browse Portrait Pic Contests</a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6">
				<div class="popular-item">
					<div class="top-content">
						<div class="icon">
							<img src="assets/images/icon-04.png" alt="">
						</div>
						<div class="right">
							<h4>Space Pic Contest</h4>
							<span><em>135</em> Available Contests</span>
						</div>
					</div>
					<div class="thumb">
						<img src="assets/images/popular-04.png" alt=""> <span
							class="category">Top Contest</span> <span class="likes"><i
							class="fa fa-heart"></i> 256</span>
					</div>
					<div class="border-button">
						<a href="contest-details.jsp">Browse Space Pic Contests</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<section class="closed-contests">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-heading text-center">
					<h6>Closed Photography Contests</h6>
					<h4>
						<em>Previous Contests</em> With Handpicked <em>Winners</em>
					</h4>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="owl-features owl-carousel"
					style="position: relative; z-index: 5;">
					<div class="item">
						<div class="closed-item">
							<div class="thumb">
								<img src="assets/images/closed-01.jpg" alt=""> <span
									class="winner"><em>Winner:</em> Anthony Soft</span> <span
									class="price"><em>Award :</em> $1,600</span>
							</div>
							<div class="down-content">
								<div class="row">
									<div class="col-7">
										<h4>
											88 Participants <br>
											<span>Number Of Artists</span>
										</h4>
									</div>
									<div class="col-5">
										<h4 class="pics">
											320 Pictures <br>
											<span>Submited Pics</span>
										</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="closed-item">
							<div class="thumb">
								<img src="assets/images/closed-02.jpg" alt=""> <span
									class="winner"><em>Winner:</em> Anthony Soft</span> <span
									class="price"><em>Award :</em> $4,200</span>
							</div>
							<div class="down-content">
								<div class="row">
									<div class="col-7">
										<h4>
											96 Participants <br>
											<span>Number Of Artists</span>
										</h4>
									</div>
									<div class="col-5">
										<h4 class="pics">
											410 Pictures <br>
											<span>Submited Pics</span>
										</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="closed-item">
							<div class="thumb">
								<img src="assets/images/closed-03.jpg" alt=""> <span
									class="winner"><em>Winner:</em> Anthony Soft</span> <span
									class="price"><em>Award :</em> $3,200</span>
							</div>
							<div class="down-content">
								<div class="row">
									<div class="col-7">
										<h4>
											74 Participants <br>
											<span>Number Of Artists</span>
										</h4>
									</div>
									<div class="col-5">
										<h4 class="pics">
											284 Pictures <br>
											<span>Submited Pics</span>
										</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="border-button text-center">
					<a href="contests.jsp">Browse Open Contests</a>
				</div>
			</div>
		</div>
	</div>
</section>

<section class="pricing-plans">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-heading text-center">
					<h6>Our Pricing</h6>
					<h4>
						Photography <em>Contest Plans</em> and Price <em>Awards</em>
					</h4>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="pricing-item">
					<img src="assets/images/pricing-01.jpg" alt="">
					<h4>Basic Plan</h4>
					<ul class="first-plan">
						<li>Lorem Ipsum Dolores Sonte</li>
						<li>Songe Lorem Ipsum Dol</li>
						<li>Matrios Venga Heptuss</li>
						<li>Denim Sriracha Kogi</li>
						<li>Digital Photography Awards</li>
					</ul>
					<span class="price">$25 USD</span>
					<div class="border-button">
						<a href="#">Choose This Plan</a>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="pricing-item">
					<img src="assets/images/pricing-02.jpg" alt="">
					<h4>Standard Plan</h4>
					<ul class="second-plan">
						<li>Lorem Ipsum Dolores Sonte</li>
						<li>Songe Lorem Ipsum Dol</li>
						<li>Matrios Venga Heptuss</li>
						<li>Denim Sriracha Kogi</li>
						<li>Digital Photography Awards</li>
					</ul>
					<span class="price">$45 USD</span>
					<div class="border-button">
						<a href="#">Choose This Plan</a>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="pricing-item">
					<img src="assets/images/pricing-03.jpg" alt="">
					<h4>Advanced Plan</h4>
					<ul class="third-plan">
						<li>Lorem Ipsum Dolores Sonte</li>
						<li>Songe Lorem Ipsum Dol</li>
						<li>Matrios Venga Heptuss</li>
						<li>Denim Sriracha Kogi</li>
						<li>Digital Photography Awards</li>
					</ul>
					<span class="price">$85 USD</span>
					<div class="border-button">
						<a href="#">Choose This Plan</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<p>
					Copyright © 2048 <a href="#">SnapX</a> Photo Contest Co., Ltd. All
					rights reserved. Design: <a title="CSS Templates" rel="sponsored"
						href="https://templatemo.com/page/1" target="_blank">TemplateMo</a>
					Distribution: <a title="CSS Templatesss" rel="sponsored"
						href="https://themewagon.com" target="_blank">ThemeWagon</a>
				</p>
			</div>
		</div>
	</div>
</footer>

<!-- Scripts -->
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<script src="assets/js/isotope.min.js"></script>
<script src="assets/js/owl-carousel.js"></script>

<script src="assets/js/tabs.js"></script>
<script src="assets/js/popup.js"></script>
<script src="assets/js/custom.js"></script>

</body>
</html>