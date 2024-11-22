<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 첨부형 게시판</title>
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
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e0f7ff;
        }
        .button-container {
            text-align: right;
            margin-bottom: 20px;
        }
        a {
            text-decoration: none;
            background-color: #0073e6;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            font-weight: bold;
        }
        a:hover {
            background-color: #005bb5;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>게시판 목록</h1>
        <div class="button-container">
            <a href="../Fmvcboard/write.do">글쓰기</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${boardLists}" var="row">
                    <tr>
                        <td>${row.idx}</td>
                        <td><a href="../Fmvcboard/view.do?idx=${row.idx}">${row.title}</a></td>
                        <td>${row.id}</td>
                        <td>${row.visitcount}</td>
                        <td>${row.postdate}</td>
                        <td>
                            <c:if test="${not empty row.ofile}">
                                <a href="../Fmvcboard/download.do?ofile=${row.ofile}&sfile=${row.sfile}&idx=${row.idx}">다운로드</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
