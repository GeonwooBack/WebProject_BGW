package membership;

import java.sql.*;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public MemberDAO(String driver, String url, String id, String pwd) {
    	super(driver, url, id, pwd);
    }
    public MemberDAO(ServletContext application) {
    	super(application);
    }
    
    // 데이터베이스 연결 설정
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle DB URL
    private final String DB_USER = "webproject_db"; // 사용자 이름
    private final String DB_PASSWORD = "1234"; // 비밀번호

    // 데이터베이스 연결 메서드
    private Connection getConnection() {
        try {
            // Oracle JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결에 실패했습니다.");
            e.printStackTrace();
        }
        return null;
    }

    // 회원가입 메서드
    public boolean registerMember(MemberDTO member) {
        String sql = "INSERT INTO users (username, password, name, email, phone_number, birthdate, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, member.getUsername());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setString(4, member.getEmail());
                pstmt.setString(5, member.getPhoneNumber()); // phone_number에 맞게 수정
                pstmt.setDate(6, member.getBirthdate());
                pstmt.setString(7, member.getGender());

                

                System.out.println("===================회원가입 dao 적용확인=====================");
                System.out.println(member.getUsername());
                System.out.println(member.getPassword());
                System.out.println(member.getName());
                System.out.println(member.getEmail());
                System.out.println(member.getPhoneNumber());
                System.out.println(member.getBirthdate());
                System.out.println(member.getGender());
                int result = pstmt.executeUpdate();

                if (result > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            System.err.println("회원가입 중 오류 발생!");
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            closeAll();
        }
        return false;
    }

    // 회원 정보 조회 메서드
    public MemberDTO getMember(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            conn = getConnection();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    return new MemberDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone_number"), // phone_number에 맞게 수정
                        rs.getString("gender"),
                        rs.getDate("birthdate")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("회원 정보 조회 중 오류 발생!");
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return null;
    }

    // 리소스 닫기 메서드
    private void closeAll() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("리소스 닫기 중 오류 발생!");
            e.printStackTrace();
        }
    }
}
