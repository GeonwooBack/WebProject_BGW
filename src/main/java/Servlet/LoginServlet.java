package Servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자로부터 입력받은 데이터 가져오기
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 로그인 검증 로직 (예: DB에서 확인)
        boolean isValidUser = validateUser(username, password);

        if (isValidUser) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 로그인 성공 시 홈 페이지로 리다이렉트
            response.sendRedirect("home.jsp");
        } else {
            // 로그인 실패 시 로그인 페이지로 리다이렉트
            response.sendRedirect("login.jsp?error=true");
        }
    }

    // 사용자 인증을 위한 예시 메서드
    private boolean validateUser(String username, String password) {
        // 여기서 DB 확인하거나 간단한 예시로 로그인 인증 구현
        return "admin".equals(username) && "password123".equals(password);
    }
}

