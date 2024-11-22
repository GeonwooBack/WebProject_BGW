<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 수정</title>
</head>
<body>
    <h1>게시글 수정</h1>
    <form action="EditController" method="post">
        <input type="hidden" name="id" value="${post.id}">
        <p>제목: <input type="text" name="title" value="${post.title}" required></p>
        <p>내용:</p>
        <textarea name="content" rows="10" cols="50" required>${post.content}</textarea>
        <br>
        <button type="submit">수정</button>
    </form>
    <a href="ListPage.jsp">목록으로</a>
</body>
</html>
