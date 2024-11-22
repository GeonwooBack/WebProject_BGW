package board;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 요청 파라미터 가져오기
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = request.getParameter("writer");

        try (BoardDAO dao = new BoardDAO(getServletContext())) {
            BoardDTO dto = new BoardDTO();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setWriter(writer);

            boolean result = dao.insertPost(dto);

            if (result) {
                // 작성 성공 시 목록 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/board/ListPage.do");
            } else {
                // 작성 실패 시 알림 출력
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('글 작성에 실패했습니다.'); history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 알림 출력
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('서버 오류가 발생했습니다. 관리자에게 문의하세요.'); history.back();</script>");
        }
    }
}

