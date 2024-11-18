package Servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    				throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 현재 세션 가져오기
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        response.sendRedirect("index.jsp"); // 로그아웃 후 메인 페이지로 리다이렉트
    }
}
