<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="author" content="templatemo">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <title>SnapX Photography by TemplateMo</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-snapx-photography.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
<!--

TemplateMo 576 SnapX Photography

https://templatemo.com/tm-576-snapx-photography

-->
  </head>

<body>

<%
out.println("JSP로 변경하기");
%>
리퀘스트영역 : ${ Message }$

  <!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="index.jsp" class="logo">
                        <img src="assets/images/logo.png" alt="SnapX Photography Template">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li><a href="index.jsp" class="active">Home</a></li>
                        <li class="has-sub">
                            <a href="javascript:void(0)">Photos &amp; Videos</a>
                            <ul class="sub-menu">
                                <li><a href="contests.jsp">Contests</a></li>
                                <li><a href="contest-details.jsp">Single Contest</a></li>
                            </ul>
                        </li>
                        <li><a href="categories.jsp">Categories</a></li>
                        <li><a href="users.jsp">Users</a></li>
                    </ul>
                    <div class="border-button">
                        <a id="modal_trigger" href="#modal" class="sign-in-up"><i class="fa fa-user"></i> Sign In/Up</a>
                    </div>
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>

<!-- Modal HTML -->
<div id="modal" class="popupContainer" style="display:none;">
    <div class="popupHeader">
        <span class="header_title">Login</span>
        <span class="modal_close"><i class="fa fa-times"></i></span>
    </div>

    <section class="popupBody">
        <div class="social_login">
            <div class="action_btns">
                <div class="one_half">
                    <a href="#" id="login_form" class="btn">로그인</a>
                </div>
                <div class="one_half last">
                    <a href="#" id="register_form" class="btn">회원가입</a>
                </div>
            </div>
        </div>

        <div class="user_login">
            <form id="loginForm" action="LoginServlet" method="post">
                <div>
                    <label for="username">아이디:</label>
                    <input type="text" name="username" id="username" required><br><br>
                </div>
                <div>
                    <label for="password">비밀번호:</label>
                    <input type="password" name="password" id="password" required><br><br>
                </div>
                <div class="checkbox">
                    <input id="remember" type="checkbox" />
                    <label for="remember">Remember me on this computer</label>
                </div>

                <!-- 메시지 표시 영역 -->
                <div id="loginMessage" style="color: red; margin-top: 10px; display: none;"></div>

                <div class="action_btns">
                    <div class="one_half">
                        <a href="#" class="btn back_btn"><i class="fa fa-angle-double-left"></i>뒤로가기</a>
                    </div>
                    <div class="one_half last">
                        <button type="submit" class="btn btn_red">로그인</button>
                    </div>
                </div>
                <a href="#" class="forgot_password">비밀번호를 잊어버리셨나요??</a>
            </form>
        </div>
    </section>
</div>

<!-- JavaScript -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const modal = document.getElementById('modal');
        const loginButton = document.getElementById('modal_trigger');
        const closeModal = document.querySelector('.modal_close');
        const loginForm = document.getElementById('loginForm');
        const loginMessage = document.getElementById('loginMessage'); // 메시지 표시 영역

        // 모달 열기
        loginButton.addEventListener('click', function (event) {
            event.preventDefault();
            modal.style.display = 'block';
            loginMessage.style.display = 'none'; // 메시지 숨기기
        });

        // 모달 닫기
        closeModal.addEventListener('click', function () {
            modal.style.display = 'none';
        });

        // 로그인 폼 제출 시 처리
        loginForm.addEventListener('submit', function (event) {
            const username = document.getElementById('username').value.trim();
            const password = document.getElementById('password').value.trim();

            // 기본 검증
            if (!username || !password) {
                event.preventDefault(); // 폼 제출 중단
                loginMessage.style.display = 'block';
                loginMessage.textContent = '아이디와 비밀번호를 입력해주세요.';
                return;
            }

            // 성공 메시지 표시
            event.preventDefault(); // 디버깅용 (서버 전송 시 제거)
            loginMessage.style.display = 'block';
            loginMessage.style.color = 'green';
            loginMessage.textContent = `환영합니다, ${username}님!`;
        });
    });
</script>


			<!-- Register Form -->
        <div class="user_register">
            <form action="" method="post">
                <label>Username</label>
                <input name="username" type="text" id="username" />
                <br />

                <label>Email Address</label>
                <input name="email" type="email" id="email" />
                <br />

                <label>Password</label>
                <input name="password" type="password" id="password" />
                <br />

                <div class="checkbox">
                    <input id="send_updates" type="checkbox" />
                    <label for="send_updates">Send me occasional email updates</label>
                </div>

                <div class="action_btns">
                    <div class="one_half"><a href="#" class="btn back_btn"><i class="fa fa-angle-double-left"></i> Back</a></div>
                    <div class="one_half last"><button type="submit" class="btn btn_red">Register</button></div>
                </div>
            </form>
        </div>
        
    </section>
  </div>

  <!-- ***** Main Banner Area Start ***** -->
  <div class="main-banner">
    <div class="container">
      <div class="row">
        <div class="col-lg-10 offset-lg-1">
          <div class="header-text">
            <h2>Enter a world of <em>Photos</em> &amp; Amazing <em>Awards</em></h2>
            <p>SnapX Photography is a professional website template with 5 different HTML pages for maximum customizations. It is free for commercial usage. This Bootstrap v5.1.3 CSS layout is provided by TemplateMo Free CSS Templates.</p>
            <div class="buttons">
              <div class="big-border-button">
                <a href="contests.jsp">Explore SnapX Contest</a>
              </div>
              <div class="icon-button">
                <a href="https://youtube.com/templatemo" target="_blank"><i class="fa fa-play"></i> Watch Our Video Now</a>
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
          <div class="owl-features owl-carousel" style="position: relative; z-index: 5;">
            <div class="item">
              <div class="thumb">
                <img src="assets/images/featured-01.jpg" alt="">
                <div class="hover-effect">
                  <div class="content">
                    <h4>Walk In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Smile In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Happy In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Walk In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Run In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Stay In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Walk In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Shoot In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
                    <h4>Fly In The Nature <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> <span>(4.5)</span></h4>
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
            <h4>Check Out <em>Popular</em> Contest <em>Categories</em></h4>
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
              <img src="assets/images/popular-01.png" alt="">
              <span class="category">Top Contest</span>
              <span class="likes"><i class="fa fa-heart"></i> 256</span>
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
              <img src="assets/images/popular-02.png" alt="">
              <span class="category">Top Contest</span>
              <span class="likes"><i class="fa fa-heart"></i> 256</span>
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
              <img src="assets/images/popular-03.png" alt="">
              <span class="category">Top Contest</span>
              <span class="likes"><i class="fa fa-heart"></i> 256</span>
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
              <img src="assets/images/popular-04.png" alt="">
              <span class="category">Top Contest</span>
              <span class="likes"><i class="fa fa-heart"></i> 256</span>
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
            <h4><em>Previous Contests</em> With Handpicked <em>Winners</em></h4>
          </div>
        </div>
        <div class="col-lg-12">
          <div class="owl-features owl-carousel" style="position: relative; z-index: 5;">
            <div class="item">
              <div class="closed-item">
                <div class="thumb">
                  <img src="assets/images/closed-01.jpg" alt="">
                  <span class="winner"><em>Winner:</em> Anthony Soft</span>
                  <span class="price"><em>Award :</em> $1,600</span>
                </div>
                <div class="down-content">
                  <div class="row">
                    <div class="col-7">
                      <h4>88 Participants <br><span>Number Of Artists</span></h4>
                    </div>
                    <div class="col-5">
                      <h4 class="pics">320 Pictures <br><span>Submited Pics</span></h4>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="item">
              <div class="closed-item">
                <div class="thumb">
                  <img src="assets/images/closed-02.jpg" alt="">
                  <span class="winner"><em>Winner:</em> Anthony Soft</span>
                  <span class="price"><em>Award :</em> $4,200</span>
                </div>
                <div class="down-content">
                  <div class="row">
                    <div class="col-7">
                      <h4>96 Participants <br><span>Number Of Artists</span></h4>
                    </div>
                    <div class="col-5">
                      <h4 class="pics">410 Pictures <br><span>Submited Pics</span></h4>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="item">
              <div class="closed-item">
                <div class="thumb">
                  <img src="assets/images/closed-03.jpg" alt="">
                  <span class="winner"><em>Winner:</em> Anthony Soft</span>
                  <span class="price"><em>Award :</em> $3,200</span>
                </div>
                <div class="down-content">
                  <div class="row">
                    <div class="col-7">
                      <h4>74 Participants <br><span>Number Of Artists</span></h4>
                    </div>
                    <div class="col-5">
                      <h4 class="pics">284 Pictures <br><span>Submited Pics</span></h4>
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
            <h4>Photography <em>Contest Plans</em> and Price <em>Awards</em></h4>
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
          <p>Copyright © 2048 <a href="#">SnapX</a> Photo Contest Co., Ltd. All rights reserved. 
          
          Design: <a title="CSS Templates" rel="sponsored" href="https://templatemo.com/page/1" target="_blank">TemplateMo</a> Distribution: <a title="CSS Templatesss" rel="sponsored" href="https://themewagon.com" target="_blank">ThemeWagon</a></p>
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