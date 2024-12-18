package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

@WebServlet("/board/ListPage.do") // URL 매핑
public class ListPageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BoardDAO dao = new BoardDAO(getServletContext())) {
            Map<String, Object> map = new HashMap<>();

            // 검색 필터 처리
            String searchField = req.getParameter("searchField");
            String searchWord = req.getParameter("searchWord");
            if (searchWord != null && !searchWord.trim().isEmpty()) {
                map.put("searchField", searchField);
                map.put("searchWord", searchWord);
            }

            // 게시글 총 개수 조회
            int totalCount = dao.selectCount(map);

            // 페이지 처리
            ServletContext application = getServletContext();
            int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
            int blockPage = Integer.parseInt(application.getInitParameter("POSTS_PER_BLOCK"));

            // pageNum 기본값 설정 및 예외 처리
            int pageNum = 1;
            String pageTemp = req.getParameter("pageNum");
            try {
                if (pageTemp != null && !pageTemp.isEmpty()) {
                    pageNum = Integer.parseInt(pageTemp);
                }
            } catch (NumberFormatException e) {
                pageNum = 1; // 잘못된 pageNum 값 처리
            }

            int start = (pageNum - 1) * pageSize + 1;
            int end = pageNum * pageSize;

            // 디버깅 로그 추가
            System.out.println("pageNum: " + pageNum);
            System.out.println("start: " + start + ", end: " + end);
            System.out.println("searchField: " + searchField + ", searchWord: " + searchWord);
            System.out.println("totalCount: " + totalCount);

            // 게시글 목록 조회
            List<BoardDTO> boardLists = dao.selectListPage(start, end, map);

            // 게시글 목록 디버깅 로그
            System.out.println("boardLists: " + (boardLists != null ? boardLists.size() : "null"));

            // 페이징 처리
            String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, req.getContextPath() + "/board/ListPage.do");
            map.put("pagingImg", pagingImg);
            map.put("totalCount", totalCount);
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);

            // 데이터 전달 및 View로 포워드
            req.setAttribute("boardLists", boardLists);
            req.setAttribute("map", map);
            req.getRequestDispatcher("/Board/ListPage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(); // 서버 콘솔에 로그 출력
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>alert('목록 조회 중 오류가 발생했습니다: " + e.getMessage() + "'); history.back();</script>");
        }
    }
}

