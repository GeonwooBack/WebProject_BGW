package board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/edit.do")
public class EditController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            // 데이터 가져오기
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            // DTO에 데이터 설정
            BoardDTO post = new BoardDTO();
            post.setId(id);
            post.setTitle(title);
            post.setContent(content);

            // DAO를 이용한 게시글 수정 처리
            try (BoardDAO dao = new BoardDAO(getServletContext())) {
                boolean result = dao.updatePost(post);

                if (result) {
                    // 수정 성공: 목록 페이지로 리디렉션
                    response.sendRedirect(request.getContextPath() + "/board/ListPage.do");
                } else {
                    // 수정 실패: 에러 메시지 출력
                    response.setContentType("text/html; charset=UTF-8");
                    response.getWriter().println("<script>alert('글 수정 실패! 다시 시도해주세요.'); history.back();</script>");
                }
            }
        } catch (NumberFormatException e) {
            // 잘못된 ID 형식 예외 처리
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('잘못된 요청입니다.'); history.back();</script>");
        } catch (Exception e) {
            // 일반적인 예외 처리
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('서버 오류가 발생했습니다. 관리자에게 문의하세요.'); history.back();</script>");
        }
    }
}
