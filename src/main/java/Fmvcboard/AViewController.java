package Fmvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 게시물 열람 서블릿
@WebServlet("/Fmvcboard/view.do")
public class AViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 게시물 조회를 위한 DAO 생성
        AMVCBoardDAO dao = new AMVCBoardDAO();
        String idx = req.getParameter("idx"); // 전달받은 일련번호
        dao.updateVisitCount(idx); // 조회수 1 증가
        AMVCBoardDTO dto = dao.selectView(idx); // 게시물 정보 조회
        dao.close(); // DB 연결 닫기

        // 줄바꿈 처리: 텍스트 내용의 줄바꿈을 HTML <br/> 태그로 변환
        if (dto != null && dto.getContent() != null) {
            dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
        }

        // 파일 확장자 추출
        String fileExtension = "";
        if (dto.getOfile() != null && dto.getOfile().lastIndexOf(".") != -1) {
            fileExtension = dto.getOfile().substring(dto.getOfile().lastIndexOf(".") + 1).toLowerCase();
        }

        // JSP에 데이터 전달
        req.setAttribute("dto", dto);
        req.setAttribute("fileExtension", fileExtension);

        // View.jsp로 포워드
        req.getRequestDispatcher("/FileMVCBoard/AView.jsp").forward(req, resp);
    }
}
