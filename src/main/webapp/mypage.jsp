<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 세션에서 로그인된 사용자 정보 확인
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp"); // 로그인이 안 된 경우 로그인 페이지로 리다이렉트
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="author" content="templatemo">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <title>My Page - SnapX Photography</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-snapx-photography.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
    <style>
        body {
            background-color: #87CEFA; /* 연한 파란색 배경 */
            color: #fff;
            font-family: 'Roboto', sans-serif;
        }
        .profile-form {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            border: none;
            border-radius: 15px;
            background: linear-gradient(145deg, #ffffff, #e6e6e6);
            box-shadow: 0px 10px 15px rgba(0, 0, 0, 0.1);
            color: #333;
        }
        h1, h2 {
            text-align: center;
            color: #333;
        }
        .btn-custom {
            background-color: #007bff;
            border: none;
            color: white;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 18px;
            margin: 20px 0;
            cursor: pointer;
            border-radius: 25px;
            transition: background-color 0.3s, transform 0.3s;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            transform: translateY(-3px);
        }
        .form-control {
            border-radius: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1><%= session.getAttribute("name") %>님 환영합니다!</h1>
        <div class="profile-form">
            <h2>내 정보 수정 및 비밀번호 변경</h2>
            <form action="UpdateProfileServlet" method="post">
                <div class="form-group">
                    <label for="nickname">닉네임:</label>
                    <input type="text" id="nickname" name="nickname" class="form-control" value="<%= username %>" required>
                </div>
                
                <div class="form-group">
                    <label for="currentPassword">현재 비밀번호:</label>
                    <input type="password" id="currentPassword" name="currentPassword" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="newPassword">새 비밀번호:</label>
                    <input type="password" id="newPassword" name="newPassword" class="form-control">
                </div>
                <button type="submit" class="btn-custom">정보 수정</button>
            </form>
        </div>
        <div class="text-right mt-4">
            <a href="index.jsp" class="btn-custom" style="float: right;">홈으로 가기</a>
        </div>
    </div>
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>
