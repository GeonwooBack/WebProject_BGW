<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jakarta.servlet.http.HttpSession" %>
<%
    // JSP 기본 제공 session 객체 사용
    String username = (String) session.getAttribute("username");

    if (username == null) {
        // 로그인 상태가 아니라면 index.jsp로 리다이렉트
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>환영합니다</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #e6f2ff, #cce0ff);
            font-family: 'Poppins', sans-serif;
            color: #003366;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 100%;
        }
        h1 {
            color: #0059b3;
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        a {
            color: #0059b3;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }
        a:hover {
            color: #003366;
        }
        form {
            margin-top: 20px;
        }
        button {
            background: linear-gradient(to right, #0073e6, #005bb5);
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 30px;
            cursor: pointer;
            font-size: 1.1em;
            transition: background 0.3s, transform 0.2s;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        button:hover {
            background: linear-gradient(to right, #005bb5, #003f7f);
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>환영합니다, <%= username %>님!</h1>
        <a href="logout.jsp">로그아웃</a><br><br>
        <form action="index.jsp" method="get">
            <button type="submit">계속</button>
        </form>
    </div>
</body>
