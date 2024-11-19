package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 현재 세션 가져오기
        HttpSession session = request.getSession(false); // 기존 세션을 가져옵니다. 없으면 null 반환.
        if (session != null) {
            session.invalidate(); // 세션 무효화 (로그아웃)
        }
        // 로그아웃 후 메인 페이지로 리다이렉트
        response.sendRedirect("index.jsp");
    }
}
