package Servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    				throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 임시 로그인 검증 로직 (DB 조회로 대체 가능)
        if ("admin".equals(username) && "1234".equals(password)) {
            HttpSession session = request.getSession(); // 세션 생성
            session.setAttribute("userId", username); // 세션에 사용자 정보 저장

            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true, \"userId\": \"" + username + "\"}");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false}");
        }
    }
}
