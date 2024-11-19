<%@page import="java.sql.Connection"%>
<%@page import="common.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB 연결 테스트</title>
</head>
<body>
<%
    Connection conn = null;
    try {
        // DB 연결 시도
        conn = DBConnect.getConnection();
        if (conn != null) {
            out.println("<p>DB 연결 성공!</p>");
        } else {
            out.println("<p>DB 연결 실패!</p>");
        }
    } catch (Exception e) {
        // 오류 메시지 출력
        out.println("<p>DB 연결 중 오류가 발생했습니다: " + e.getMessage() + "</p>");
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                // 연결 종료
                conn.close();
                out.println("<p>DB 연결 종료 성공!</p>");
            } catch (Exception e) {
                // 연결 종료 시 오류 처리
                out.println("<p>DB 연결 종료 중 오류가 발생했습니다: " + e.getMessage() + "</p>");
                e.printStackTrace();
            }
        }
    }
%>
</body>
</html>
