package Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청과 응답 문자셋 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 세션에서 현재 로그인된 사용자 확인
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
            response.getWriter().println("<script>alert('로그인이 필요합니다.'); location.href='index.jsp';</script>");
            return;
        }

        // 폼에서 전달된 새로운 비밀번호 가져오기
        String newPassword = request.getParameter("newPassword");

        // DAO를 통해 비밀번호 업데이트
        MemberDAO dao = new MemberDAO(getServletContext());
        boolean isUpdated = dao.updatePassword(username, newPassword);

        if (isUpdated) {
            // 업데이트 성공 시
            response.getWriter().println("<script>alert('비밀번호가 성공적으로 변경되었습니다.'); location.href='mypage.jsp';</script>");
        } else {
            // 업데이트 실패 시
            response.getWriter().println("<script>alert('비밀번호 변경에 실패하였습니다. 다시 시도해주세요.'); location.href='mypage.jsp';</script>");
        }
    }
}
