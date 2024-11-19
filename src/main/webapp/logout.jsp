<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jakarta.servlet.http.HttpSession" %>
<%
    // JSP 기본 session 객체를 사용
    if (session != null) {
        session.invalidate(); // 세션 무효화
    }

    // JavaScript로 알림창 표시 후 index.jsp로 이동
    response.setContentType("text/html;charset=UTF-8");
    out.println("<script>");
    out.println("alert('로그아웃되었습니다.');");
    out.println("location.href='index.jsp';");
    out.println("</script>");
%>
