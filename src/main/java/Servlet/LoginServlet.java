package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청과 응답 문자셋 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 폼 데이터 가져오기
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // MemberDAO 객체 생성 (ServletContext 사용)
        ServletContext application = getServletContext();
        MemberDAO dao = new MemberDAO(application);

        // 로그인 검증
        boolean isValid = dao.login(username, password);

        if (isValid) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 로그인 성공 후 index.jsp로 리다이렉트
            response.getWriter().println("<script>alert('" + username + "님 환영합니다!'); location.href='index.jsp';</script>");
        } else {
            // 로그인 실패 시 index.jsp로 리다이렉트
            response.getWriter().println("<script>alert('로그인에 실패하였습니다.'); location.href='index.jsp';</script>");
        }
    }
}
