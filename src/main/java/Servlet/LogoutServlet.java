package Servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 현재 세션을 가져오기
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 세션 무효화
            session.invalidate();
        }

        // 로그아웃 후 로그인 페이지로 리다이렉트
        response.sendRedirect("login.jsp");
    }
}
