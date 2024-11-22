package board;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 매개변수 가져오기
        String ofile = req.getParameter("ofile"); // 원본 파일명
        String sfile = req.getParameter("sfile"); // 저장된 파일명
        String idx = req.getParameter("idx");    // 게시물 번호

        // 파일 다운로드 수행
        FileUtil.download(req, resp, "/Uploads", sfile, ofile);

        // 해당 게시글의 다운로드 수 증가
        try (BoardDAO dao = new BoardDAO(getServletContext())) { // ServletContext로 DAO 생성
            dao.downCountPlus(idx); // 다운로드 수 증가 메서드 호출
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
