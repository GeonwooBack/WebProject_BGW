<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 보기</title>
</head>
<body>
    <h1>${post.title}</h1>
    <p>작성자: ${post.writer}</p>
    <p>작성일: ${post.postDate}</p>
    <p>${post.content}</p>
    <a href="Edit.jsp?id=${post.id}">수정</a>
    <form action="DeleteController" method="post" style="display:inline;">
        <input type="hidden" name="id" value="${post.id}">
        <button type="submit">삭제</button>
    </form>
    <a href="/Board/ListPage.jsp">목록으로</a>
</body>
</html>
