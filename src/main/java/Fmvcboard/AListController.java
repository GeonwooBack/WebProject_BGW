package Fmvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Fmvcboard/list.do")
public class AListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        // DAO 인스턴스 생성
        AMVCBoardDAO dao = new AMVCBoardDAO();

        // 검색어 관련 파라미터 저장
        Map<String, Object> map = new HashMap<String, Object>();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");
        if (searchWord != null && !searchWord.isEmpty()) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        // 총 게시물 개수 가져오기
        int totalCount = dao.selectCount();
        map.put("totalCount", totalCount);

        // 현재 페이지와 페이지당 게시물 수 설정
        int pageSize = 10; // 페이지당 게시물 수
        int currentPage = Integer.parseInt(req.getParameter("pageNum") != null ? req.getParameter("pageNum") : "1");
        int start = (currentPage - 1) * pageSize + 1;
        int end = currentPage * pageSize;

        // 게시물 목록 가져오기
        List<AMVCBoardDTO> boardLists = dao.selecList(start, end);

        // DB 연결 해제
        dao.close();

        // view로 전달할 데이터 설정
        req.setAttribute("boardLists", boardLists);
        req.setAttribute("map", map);

        // JSP로 포워드
        req.getRequestDispatcher("/FileMVCBoard/AList.jsp").forward(req, resp);
    }
}
