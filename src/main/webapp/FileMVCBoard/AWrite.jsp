<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
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
        }
        td {
            padding: 10px;
        }
        input[type="text"], textarea, input[type="file"] {
            width: 95%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button[type="submit"] {
            background-color: #0073e6;
            color: white;
        }
        button[type="reset"] {
            background-color: #f44336;
            color: white;
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
        <h1>글쓰기</h1>
        <form name="writeFrm" method="post" enctype="multipart/form-data" action="../Fmvcboard/write.do">
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title"></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td><textarea name="content" rows="5"></textarea></td>
                </tr>
                <tr>
                    <td>첨부 파일</td>
                    <td><input type="file" name="ofile"></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;">
                        <button type="submit">작성 완료</button>
                        <button type="reset">초기화</button>
                        <button type="button" onclick="location.href='../Fmvcboard/list.do';">목록 바로가기</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
