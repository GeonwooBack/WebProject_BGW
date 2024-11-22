package board;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 요청에서 ID 파라미터 가져오기
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("잘못된 게시글 ID입니다.");
            }

            int id = Integer.parseInt(idParam);

            // DAO를 사용하여 게시글 조회
            try (BoardDAO dao = new BoardDAO(getServletContext())) {
                BoardDTO post = dao.getPost(id);

                if (post == null) {
                    // 게시글이 존재하지 않는 경우
                    response.sendRedirect(request.getContextPath() + "/Board/ListPage.jsp?error=notfound");
                    return;
                }

                // 게시글 데이터를 요청에 설정하고 View.jsp로 포워드
                request.setAttribute("post", post);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/View.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            // ID가 숫자가 아닌 경우 처리
            response.sendRedirect(request.getContextPath() + "/Board/ListPage.jsp?error=invalidid");
        } catch (Exception e) {
            e.printStackTrace();
            // 일반적인 서버 오류 처리
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('게시글 조회 중 오류가 발생했습니다.'); history.back();</script>");
        }
    }
}
