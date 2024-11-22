package Fmvcboard; // 기존 패키지 이름 유지

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/FileMVCBoard/AEdit.do") // URL 매핑 수정
@MultipartConfig(maxFileSize = 1024 * 1024 * 1, maxRequestSize = 1024 * 1024 * 10)
public class AEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 로그인 확인
        HttpSession session = req.getSession();
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../FileMVCBoard/ADefault.jsp"); // 경로 수정
            return;
        }

        // 게시물 얻어오기
        String idx = req.getParameter("idx");
        AMVCBoardDAO dao = new AMVCBoardDAO();
        AMVCBoardDTO dto = dao.selectView(idx);

        // 작성자 본인 확인
        if (!dto.getId().equals(session.getAttribute("UserId").toString())) {
            JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
            return;
        }

        // 작성자 본인이라면 request 영역에 DTO를 저장한 후 포워드한다.
        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/FileMVCBoard/AEdit.jsp").forward(req, resp); // 경로 수정
    }

    // 수정 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 확인
        HttpSession session = req.getSession();
        if (session.getAttribute("UserId") == null) {
            JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../FileMVCBoard/ADefault.jsp"); // 경로 수정
            return;
        }

        // 작성자 본인 확인
        if (!req.getParameter("id").equals(session.getAttribute("UserId").toString())) {
            JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
            return;
        }

        // 파일 업로드 처리
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");
        String originalFileName = "";
        try {
            originalFileName = FileUtil.uploadFile(req, saveDirectory);
        } catch (Exception e) {
            JSFunction.alertBack(resp, "파일 업로드 오류입니다.");
            return;
        }

        // 수정할 내용 가져오기
        String idx = req.getParameter("idx");
        String prevOfile = req.getParameter("prevOfile");
        String prevSfile = req.getParameter("prevSfile");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        // DTO에 데이터 설정
        AMVCBoardDTO dto = new AMVCBoardDTO();
        dto.setIdx(idx);
        dto.setId(session.getAttribute("UserId").toString());
        dto.setTitle(title);
        dto.setContent(content);

        // 파일 처리
        if (!originalFileName.isEmpty()) {
            String saveFileName = FileUtil.renameFile(saveDirectory, originalFileName);
            dto.setOfile(originalFileName);
            dto.setSfile(saveFileName);
            FileUtil.deleteFile1(req, "/Uploads", prevSfile);
        } else {
            dto.setOfile(prevOfile);
            dto.setSfile(prevSfile);
        }

        // DB 업데이트
        AMVCBoardDAO dao = new AMVCBoardDAO();
        int result = dao.updatePost(dto);
        dao.close();

        // 결과 처리
        if (result == 1) { // 수정 성공
            resp.sendRedirect("../FileMVCBoard/AView.do?idx=" + idx); // 경로 수정
        } else { // 수정 실패
            JSFunction.alertLocation(resp, "수정에 실패했습니다. 다시 시도해주세요.", "../FileMVCBoard/AView.do?idx=" + idx); // 경로 수정
        }
    }
}
