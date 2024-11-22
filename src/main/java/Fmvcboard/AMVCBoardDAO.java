package Fmvcboard; // 기존 패키지명 유지

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool; // 공통 DB 연결 클래스

// DBCP(커넥션 풀)를 통해 Oracle에 연결하기 위해 상속을 받아 정의
public class AMVCBoardDAO extends DBConnPool {

    // 기본 생성자
    public AMVCBoardDAO() {
        super(); // 부모 클래스 생성자 호출
    }

    // 게시물의 갯수를 카운트하기 위한 메서드
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM mvcboard";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") 
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }
        return totalCount;
    }

    // 게시판 목록을 출력하기 위한 메서드
    public List<AMVCBoardDTO> selecList(Map<String, Object> map) {
        List<AMVCBoardDTO> board = new Vector<>();
        String query = "SELECT * FROM mvcboard ";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") 
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY idx DESC"; // 최신 게시물이 상단에 노출
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {
                AMVCBoardDTO dto = new AMVCBoardDTO();
                dto.setIdx(rs.getString(1));
                dto.setId(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setVisitcount(rs.getInt(9));
                board.add(dto);
            }
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }

    // 게시물 작성
    public int insertWrite(AMVCBoardDTO dto) {
        int result = 0;
        try {
            String query = "INSERT INTO mvcboard (idx, id, title, content, ofile, sfile) " 
                         + " VALUES (seq_board_num.NEXTVAL, ?, ?, ?, ?, ?)";
            psmt = con.prepareStatement(query);
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

    // 게시물 상세 보기
    public AMVCBoardDTO selectView(String idx) {
        AMVCBoardDTO dto = new AMVCBoardDTO();
        String query = "SELECT Bo.*, Me.name FROM mvcboard Bo "
                     + " INNER JOIN member Me ON Bo.id = Me.id WHERE idx = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            rs = psmt.executeQuery();
            if (rs.next()) {
                dto.setIdx(rs.getString(1));
                dto.setId(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setVisitcount(rs.getInt(9));
                dto.setName(rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("게시물 상세 보기 중 예외 발생");
            e.printStackTrace();
        }
        return dto;
    }

    // 조회수 증가
    public void updateVisitCount(String idx) {
        String query = "UPDATE mvcboard SET visitcount = visitcount + 1 WHERE idx = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("조회수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    // 다운로드 카운트 증가
    public void downCountPlus(String idx) {
        String query = "UPDATE mvcboard SET downcount = downcount + 1 WHERE idx = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("다운로드 카운트 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    // 게시물 삭제
    public int deletePost(String idx) {
        int result = 0;
        String query = "DELETE FROM mvcboard WHERE idx = ?";
        try {
            psmt = con.prepareStatement(query);
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
        String query = "UPDATE mvcboard SET title = ?, content = ?, ofile = ?, sfile = ? " 
                     + " WHERE idx = ? AND id = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getContent());
            psmt.setString(3, dto.getOfile());
            psmt.setString(4, dto.getSfile());
            psmt.setString(5, dto.getIdx());
            psmt.setString(6, dto.getId());
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("게시물 수정 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // 페이징 처리
    public List<AMVCBoardDTO> selectListPage(Map<String, Object> map) {
        List<AMVCBoardDTO> board = new Vector<>();
        String query = "SELECT * FROM (SELECT Tb.*, ROWNUM rNum FROM (SELECT * FROM mvcboard ";
        if (map.get("searchWord") != null) {
            query += "WHERE " + map.get("searchField") 
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += "ORDER BY idx DESC) Tb) WHERE rNum BETWEEN ? AND ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, map.get("start").toString());
            psmt.setString(2, map.get("end").toString());
            rs = psmt.executeQuery();
            while (rs.next()) {
                AMVCBoardDTO dto = new AMVCBoardDTO();
                dto.setIdx(rs.getString(1));
                dto.setId(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setVisitcount(rs.getInt(9));
                board.add(dto);
            }
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }
}
