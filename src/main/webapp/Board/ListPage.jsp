    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>자유게시판</title>
    <!-- 스타일 추가 -->
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
        .button-container {
            text-align: right;
            margin-bottom: 10px;
        }
        .button-container a {
            display: inline-block;
            padding: 8px 16px;
            margin: 0 5px;
            text-decoration: none;
            background-color: #0073e6;
            color: #ffffff;
            font-weight: bold;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
        .button-container a:hover {
            background-color: #005bb5;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 10px;
        }
        th {
            background-color: #0073e6;
            color: #ffffff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e0f7ff;
        }
        a {
            text-decoration: none;
            color: #0073e6;
            font-weight: bold;
        }
        a:hover {
            color: #005bb5;
        }
        .no-posts {
            text-align: center;
            color: #888888;
            font-size: 18px;
            margin: 20px 0;
        }
        .action-buttons a {
            margin: 0 5px;
            padding: 4px 8px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="header">
        자유게시판
        <div class="auth-buttons">
            <% 
            String username = (String) session.getAttribute("username");
            if (username != null) { 
            %>
                <a href="LogoutServlet">로그아웃</a>
            <% } else { %>
                <a href="index.jsp">로그인</a>
                <a href="index.jsp">회원가입</a>
            <% } %>
        </div>
    </div>
    <div class="container">
        <h1>자유게시판</h1>
        <div class="button-container">
            <% if (username != null) { %>
                <a href="<%= request.getContextPath() %>/Board/Write.jsp" class="btn btn-primary">글쓰기</a>
            <% } else { %>
                <span>로그인 후 글쓰기가 가능합니다.</span>
            <% } %>
        </div>
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <% if (username != null) { %>
                <th>관리</th>
                <% } %>
            </tr>
            <%
            List<board.BoardDTO> posts = (List<board.BoardDTO>) request.getAttribute("posts");
            if (posts == null || posts.isEmpty()) {
            %>
            <tr>
                <td colspan="<%= (username != null) ? 5 : 4 %>">게시글이 없습니다.</td>
            </tr>
            <% } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                for (board.BoardDTO post : posts) {
            %>
            <tr>
                <td><%= post.getId() %></td>
                <td><a href="ViewController?id=<%= post.getId() %>"><%= post.getTitle() %></a></td>
                <td><%= post.getWriter() %></td>
                <td><%= dateFormat.format(post.getPostDate()) %></td>
                <% if (username != null && username.equals(post.getWriter())) { %>
                <td>
                    <a href="Edit.jsp?id=<%= post.getId() %>">수정</a>
                    <a href="DeleteController?id=<%= post.getId() %>">삭제</a>
                </td>
                <% } %>
            </tr>
            <% } } %>
        </table>
    </div>
</body>
</html>
    