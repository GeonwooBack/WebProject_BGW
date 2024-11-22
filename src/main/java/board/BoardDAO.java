package board;

import java.sql.*;
import java.util.*;
import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect implements AutoCloseable {

    public BoardDAO(ServletContext application) {
        super(application); // JDBConnect의 생성자를 호출
    }

    @Override
    public void close() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시글 목록 조회
    public List<BoardDTO> getList(int start, int end) {
        return selectListPage(start, end, null);
    }

    // 게시글 상세 보기
    public BoardDTO getPost(int id) {
        BoardDTO dto = null;
        String sql = "SELECT * FROM board WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new BoardDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setWriter(rs.getString("writer"));
                    dto.setPostDate(rs.getTimestamp("postDate"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    // 게시글 작성
    public boolean insertPost(BoardDTO dto) {
        String sql = "INSERT INTO board (title, content, writer, created_at) VALUES (?, ?, ?, SYSDATE)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getWriter());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 게시글 수정
    public boolean updatePost(BoardDTO dto) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setInt(3, dto.getId());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 게시글 삭제
    public boolean deletePost(int id) {
        String sql = "DELETE FROM board WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 게시글 총 개수 조회
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM board";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 조건에 따라 게시글 총 개수 조회
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String sql = "SELECT COUNT(*) FROM board WHERE 1=1";
        if (map != null && map.get("searchWord") != null) {
            sql += " AND " + map.get("searchField") + " LIKE ?";
        }
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            if (map != null && map.get("searchWord") != null) {
                pstmt.setString(1, "%" + map.get("searchWord") + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalCount = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    // 페이징 처리 메서드
    public List<BoardDTO> selectListPage(int start, int end, Map<String, Object> filters) {
        List<BoardDTO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT * FROM (SELECT ROWNUM rnum, A.* FROM (SELECT * FROM board");

        if (filters != null && filters.containsKey("searchWord")) {
            sql.append(" WHERE ").append(filters.get("searchField")).append(" LIKE ?");
        }

        sql.append(" ORDER BY id DESC) A) WHERE rnum BETWEEN ? AND ?");

        try (PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (filters != null && filters.containsKey("searchWord")) {
                pstmt.setString(paramIndex++, "%" + filters.get("searchWord") + "%");
            }
            pstmt.setInt(paramIndex++, start);
            pstmt.setInt(paramIndex, end);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BoardDTO dto = new BoardDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setWriter(rs.getString("writer"));
                    dto.setPostDate(rs.getTimestamp("postDate"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
