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

            String searchField = req.getParameter("searchField");
            String searchWord = req.getParameter("searchWord");
            if (searchWord != null && !searchWord.trim().isEmpty()) {
                map.put("searchField", searchField);
                map.put("searchWord", searchWord);
            }

            int totalCount = dao.selectCount(map);

            ServletContext application = getServletContext();
            int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
            int blockPage = Integer.parseInt(application.getInitParameter("POSTS_PER_BLOCK"));

            int pageNum = 1;
            String pageTemp = req.getParameter("pageNum");
            try {
                if (pageTemp != null && !pageTemp.isEmpty()) {
                    pageNum = Integer.parseInt(pageTemp);
                }
            } catch (NumberFormatException e) {
                pageNum = 1; // 기본값 설정
            }

            int start = (pageNum - 1) * pageSize + 1;
            int end = pageNum * pageSize;

            System.out.println("pageNum: " + pageNum);
            System.out.println("start: " + start + ", end: " + end);

            List<BoardDTO> boardLists = dao.selectListPage(start, end, map);

            if (boardLists == null || boardLists.isEmpty()) {
                System.out.println("boardLists is empty or null");
            } else {
                System.out.println("boardLists size: " + boardLists.size());
            }

            String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, req.getContextPath() + "/board/ListPage.do");
            map.put("pagingImg", pagingImg);
            map.put("totalCount", totalCount);
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);

            req.setAttribute("boardLists", boardLists);
            req.setAttribute("map", map);
            req.getRequestDispatcher("/Board/ListPage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(); // 서버 로그
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>alert('목록 조회 중 오류 발생: " + e.getMessage() + "'); history.back();</script>");
        }
    }
}
