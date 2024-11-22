package Fmvcboard; // 기존 패키지 이름 유지

import java.io.IOException;
import java.rmi.ServerException;

import fileupload.FileUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FileMVCBoard/ADownload.do") // URL 매핑 수정
public class ADownloadController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        // 클라이언트로부터 전달받은 파일명 및 인덱스 정보 가져오기
        String ofile = req.getParameter("ofile"); // 원본 파일명
        String sfile = req.getParameter("sfile"); // 서버에 저장된 파일명
        String idx = req.getParameter("idx"); // 게시물 인덱스

        // 파일 다운로드 처리
        FileUtil.download(req, resp, "/Uploads", sfile, ofile);

        // 다운로드 횟수 증가 처리
        AMVCBoardDAO dao = new AMVCBoardDAO();
        dao.downCountPlus(idx); // 다운로드 수 증가
        dao.close(); // DAO 닫기
    }
}
