<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 보기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
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
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 10px;
        }
        th {
            background-color: #0073e6;
            color: white;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin: 5px;
        }
        button[type="button"] {
            background-color: #4caf50;
            color: white;
        }
        button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>게시글 상세 보기</h1>
        <table>
            <tr>
                <th>번호</th>
                <td>${dto.idx}</td>
                <th>작성자</th>
                <td>${dto.name}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${dto.postdate}</td>
                <th>조회수</th>
                <td>${dto.visitcount}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td colspan="3">${dto.title}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">${dto.content}</td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <c:if test="${not empty dto.ofile}">
                        <a href="../Fmvcboard/download.do?ofile=${dto.ofile}&sfile=${dto.sfile}&idx=${dto.idx}">다운로드</a>
                    </c:if>
                </td>
            </tr>
        </table>
        <div style="text-align:center; margin-top:20px;">
            <c:if test="${UserId == dto.id}">
                <button type="button" onclick="location.href='../Fmvcboard/edit.do?idx=${dto.idx}';">수정</button>
                <button type="button" onclick="location.href='../Fmvcboard/delete.do?idx=${dto.idx}';">삭제</button>
            </c:if>
            <button type="button" onclick="location.href='../Fmvcboard/list.do';">목록으로</button>
        </div>
    </div>
</body>
</html>
