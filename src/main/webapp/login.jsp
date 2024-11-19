<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <h1>로그인</h1>
    <form action="LoginServlet" method="post">
        <label>아이디:</label>
        <input type="text" name="username" required><br><br>
        <label>비밀번호:</label>
        <input type="password" name="password" required><br><br>
        <button type="submit">로그인</button>
    </form>
</body>
</html>
