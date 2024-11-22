package board;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 삭제할 게시글 ID 가져오기
        int id = Integer.parseInt(request.getParameter("id"));

        // DAO 객체 생성
        BoardDAO dao = new BoardDAO(getServletContext());

        // 게시글 삭제 처리
        boolean result = dao.deletePost(id);

        try {
            if (result) {
                // 삭제 성공 시 목록 페이지로 리디렉션
                response.sendRedirect("ListController");
            } else {
                // 삭제 실패 시 경고 메시지 출력
                response.getWriter().println("<script>alert('글 삭제 실패!'); location.href='ListController';</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
