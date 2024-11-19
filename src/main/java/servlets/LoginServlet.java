package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 데이터베이스 연결
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("드라이버 로드 성공");

            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "webproject_db", "1234");
            System.out.println("DB 연결 성공");

            // SQL 쿼리 실행
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            System.out.println("SQL 쿼리 실행: " + sql);

            ResultSet rs = pstmt.executeQuery();

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                String name = rs.getString("name"); // 이름 가져오기
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("name", name); // 세션에 이름 저장

                out.println("<script>");
                out.println("alert('환영합니다, " + name + "님!');");
                out.println("location.href='welcome.jsp';");
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('아이디 또는 비밀번호가 잘못되었습니다. 다시 시도해주세요.');");
                out.println("location.href='index.jsp';");
                out.println("</script>");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(); // 오류 로그 콘솔에 출력

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('서버 오류가 발생했습니다. 관리자에게 문의하세요.');");
            out.println("location.href='index.jsp';");
            out.println("</script>");
        }
    }
}
