package Fmvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/Fmvcboard/write.do")
public class AWriteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 확인
        HttpSession session = req.getSession(false); // false: 세션이 없으면 null 반환
        if (session == null || session.getAttribute("UserId") == null) {
            // 로그인되지 않은 상태
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", req.getContextPath() + "/index.jsp");
            return;
        }

        // 로그인된 상태라면 AWrite.jsp로 이동
        resp.sendRedirect(req.getContextPath() + "/FileMVCBoard/AWrite.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 확인
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("UserId") == null) {
            // 로그인되지 않은 상태
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", req.getContextPath() + "/index.jsp");
            return;
        }

        // 1. 파일 업로드 처리
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");
        String originalFileName = "";

        try {
            originalFileName = FileUtil.uploadFile(req, saveDirectory);
        } catch (Exception e) {
            JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", req.getContextPath() + "/Fmvcboard/write.do");
            return;
        }

        // 2. 게시글 저장 처리
        AMVCBoardDTO dto = new AMVCBoardDTO();
        dto.setId(session.getAttribute("UserId").toString());
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));

        if (!originalFileName.isEmpty()) {
            String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
            dto.setOfile(originalFileName);
            dto.setSfile(savedFileName);
        }

        // DAO를 통해 DB에 게시글 저장
        AMVCBoardDAO dao = new AMVCBoardDAO();
        int result = dao.insertWrite(dto);
        dao.close();

        // 성공 여부에 따라 처리
        if (result == 1) { // 성공
            resp.sendRedirect(req.getContextPath() + "/Fmvcboard/list.do");
        } else { // 실패
            JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", req.getContextPath() + "/Fmvcboard/write.do");
        }
    }
}
