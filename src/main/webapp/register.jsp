<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
    <h1>회원가입</h1>
    <form action="RegisterServlet" method="post">
        <!-- 아이디 -->
        <label>아이디:</label>
        <input type="text" name="username" required><br><br>

        <!-- 비밀번호 -->
        <label>비밀번호:</label>
        <input type="password" name="password" required><br><br>

        <!-- 이름 -->
        <label>이름:</label>
        <input type="text" name="name" required><br><br>

        <!-- 생년월일 -->
        <label>생년월일:</label>
        <input type="date" name="birthdate" required><br><br>

        <!-- 이메일 -->
        <label>이메일:</label>
        <input type="email" name="email" required><br><br>

        <!-- 성별 -->
        <label>성별:</label>
        <input type="radio" id="male" name="gender" value="male" required>
        <label for="male">남성</label>
        <input type="radio" id="female" name="gender" value="female" required>
        <label for="female">여성</label><br><br>

        <!-- 제출 버튼 -->
        <button type="submit">회원가입</button>
    </form>
</body>
</html>
