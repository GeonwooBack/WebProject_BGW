package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 세션에서 사용자명 가져오기
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 폼에서 입력된 정보 가져오기
        String nickname = request.getParameter("nickname");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webproject_db", "1234");

            // 현재 비밀번호 확인
            String sql = "SELECT password FROM users WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");

                if (!dbPassword.equals(currentPassword)) {
                    // 현재 비밀번호가 일치하지 않으면 오류 처리
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().println("<script>alert('현재 비밀번호가 일치하지 않습니다.'); history.back();</script>");
                    return;
                }
            }

            // 비밀번호 업데이트
            sql = "UPDATE users SET name = ?, password = ? WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickname);

            // 새 비밀번호가 입력되지 않으면 기존 비밀번호 유지
            if (newPassword != null && !newPassword.isEmpty()) {
                pstmt.setString(2, newPassword);
            } else {
                pstmt.setString(2, currentPassword);
            }

            pstmt.setString(3, username);
            int rowUpdated = pstmt.executeUpdate();

            if (rowUpdated > 0) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>alert('정보가 성공적으로 수정되었습니다.'); location.href='mypage.jsp';</script>");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>alert('정보 수정에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>alert('서버 오류가 발생했습니다. 관리자에게 문의하세요.'); history.back();</script>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
