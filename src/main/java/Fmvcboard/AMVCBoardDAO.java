package Fmvcboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool; // 커넥션 풀 사용

public class AMVCBoardDAO extends DBConnPool {

    // 게시물 추가
    public int insertWrite(AMVCBoardDTO dto) {
        int result = 0;
        try {
            String query = "INSERT INTO mvcboard (idx, id, title, content, ofile, sfile, downcount, visitcount) "
                         + "VALUES (seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, 0, 0)";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getId());
            psmt.setString(2, dto.getTitle());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // 게시물 조회 (글 번호로 가져오기)
    public AMVCBoardDTO selectView(String idx) {
        AMVCBoardDTO dto = new AMVCBoardDTO();
        try {
            String query = "SELECT * FROM mvcboard WHERE idx = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                dto.setIdx(rs.getString("idx"));
                dto.setId(rs.getString("id"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setOfile(rs.getString("ofile"));
                dto.setSfile(rs.getString("sfile"));
                dto.setDowncount(rs.getInt("downcount"));
                dto.setVisitcount(rs.getInt("visitcount"));
            }
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return dto;
    }
 
    // 게시물 목록 반환
    public List<AMVCBoardDTO> selecList(int start, int end) {
        List<AMVCBoardDTO> boardList = new ArrayList<>();
        try {
            String query = "SELECT * FROM (SELECT ROWNUM rnum, A.* FROM "
                         + "(SELECT * FROM mvcboard ORDER BY idx DESC) A) "
                         + "WHERE rnum BETWEEN ? AND ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setInt(1, start);
            psmt.setInt(2, end);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                AMVCBoardDTO dto = new AMVCBoardDTO();
                dto.setIdx(rs.getString("idx"));
                dto.setId(rs.getString("id"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setOfile(rs.getString("ofile"));
                dto.setSfile(rs.getString("sfile"));
                dto.setDowncount(rs.getInt("downcount"));
                dto.setVisitcount(rs.getInt("visitcount"));
                boardList.add(dto);
            }
        } catch (Exception e) {
            System.out.println("게시물 목록 조회 중 예외 발생");
            e.printStackTrace();
        }
        return boardList;
    }


    // 게시물 삭제
    public int deletePost(String idx) {
        int result = 0;
        try {
            String query = "DELETE FROM mvcboard WHERE idx = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("게시물 삭제 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // 게시물 수정
    public int updatePost(AMVCBoardDTO dto) {
        int result = 0;
        try {
            String query = "UPDATE mvcboard SET title = ?, content = ?, ofile = ?, sfile = ? WHERE idx = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getContent());
            psmt.setString(3, dto.getOfile());
            psmt.setString(4, dto.getSfile());
            psmt.setString(5, dto.getIdx());
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("게시물 수정 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // 조회수 증가
    public void updateVisitCount(String idx) {
        try {
            String query = "UPDATE mvcboard SET visitcount = visitcount + 1 WHERE idx = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("조회수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    // 다운로드 횟수 증가
    public void updateDownloadCount(String idx) {
        try {
            String query = "UPDATE mvcboard SET downcount = downcount + 1 WHERE idx = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("다운로드 횟수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

 // 게시물 총 개수 반환
    public int selectCount() {
        int totalCount = 0;
        try {
            String query = "SELECT COUNT(*) FROM mvcboard";
            PreparedStatement psmt = con.prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("게시물 수 조회 중 예외 발생");
            e.printStackTrace();
        }
        return totalCount;
    }

	public List<AMVCBoardDTO> selectListPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public void downCountPlus(String idx) {
		// TODO Auto-generated method stub
		
	}
}
