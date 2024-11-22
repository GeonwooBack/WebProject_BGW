<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
        }
        .header {
            width: 100%;
            padding: 15px 0;
            background-color: #0073e6;
            color: #ffffff;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            position: relative;
        }
        .header .auth-buttons {
            position: absolute;
            top: 10px;
            right: 20px;
        }
        .auth-buttons a {
            color: #ffffff;
            text-decoration: none;
            margin: 0 5px;
            padding: 8px 16px;
            border: 1px solid #ffffff;
            border-radius: 5px;
            background-color: transparent;
        }
        .auth-buttons a:hover {
            background-color: #ffffff;
            color: #0073e6;
        }
        .container {
            width: 80%;
            margin: 50px auto;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #0073e6;
            margin-bottom: 20px;
        }
        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        form {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
        }
        form p {
            margin: 15px 0;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        textarea {
            resize: none;
        }
        button {
            padding: 10px 20px;
            background-color: #0073e6;
            color: #ffffff;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
        button:hover {
            background-color: #005bb5;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #0073e6;
            font-weight: bold;
        }
        .back-link:hover {
            color: #005bb5;
        }
    </style>
</head>
<body>
    <div class="header">
        게시글 작성
        <div class="auth-buttons">
            <% 
            String username = (String) session.getAttribute("username");
            if (username != null) { 
            %>
                <a href="<%= request.getContextPath() %>/logout.do">로그아웃</a>
            <% } else { %>
                <a href="<%= request.getContextPath() %>/index.jsp">로그인</a>
                <a href="<%= request.getContextPath() %>/index.jsp">회원가입</a>
            <% } %>
        </div>
    </div>
    <div class="container">
        <h1>게시글 작성</h1>
        <div class="form-container">
            <form action="<%= request.getContextPath() %>/board/write.do" method="post">
                <p>제목: <input type="text" name="title" required></p>
                <p>작성자: <input type="text" name="writer" required value="<%= (username != null) ? username : "" %>" readonly></p>
                <p>내용:</p>
                <textarea name="content" rows="10" required></textarea>
                <br>
                <button type="submit">작성</button>
            </form>
            <a href="<%= request.getContextPath() %>/Board/ListPage.jsp" class="back-link">목록으로</a>
        </div>
    </div>
</body>
</html>
