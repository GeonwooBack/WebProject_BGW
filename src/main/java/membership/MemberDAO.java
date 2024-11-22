package membership;

import java.sql.*;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect {
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 생성자 - JDBConnect를 상속받아 기본 DB 연결 방식 사용
    public MemberDAO(String driver, String url, String id, String pwd) {
        super(driver, url, id, pwd);
    }

    public MemberDAO(ServletContext application) {
        super(application);
    }

    // 회원가입 메서드
    public boolean registerMember(MemberDTO member) {
        String sql = "INSERT INTO users (username, password, name, email, phone_number, birthdate, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getUsername());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPhoneNumber());
            pstmt.setDate(6, member.getBirthdate());
            pstmt.setString(7, member.getGender());

            System.out.println("회원가입 데이터 확인:");
            System.out.println("username: " + member.getUsername());
            System.out.println("password: " + member.getPassword());
            System.out.println("name: " + member.getName());
            System.out.println("email: " + member.getEmail());
            System.out.println("phone_number: " + member.getPhoneNumber());
            System.out.println("birthdate: " + member.getBirthdate());
            System.out.println("gender: " + member.getGender());

            int result = pstmt.executeUpdate();
            return result > 0; // 삽입 성공 여부 반환
        } catch (SQLException e) {
            System.err.println("회원가입 중 오류 발생!");
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // 로그인 확인 메서드
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            return rs.next(); // 로그인 성공 여부 반환
        } catch (SQLException e) {
            System.err.println("로그인 중 오류 발생!");
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // 비밀번호 업데이트 메서드 추가
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);

            int result = pstmt.executeUpdate();
            return result > 0; // 업데이트 성공 여부 반환
        } catch (SQLException e) {
            System.err.println("비밀번호 업데이트 중 오류 발생!");
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    // 회원 정보 조회 메서드
    public MemberDTO getMember(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new MemberDTO(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("gender"),
                    rs.getDate("birthdate")
                );
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
        } catch (SQLException e) {
            System.err.println("리소스 닫기 중 오류 발생!");
            e.printStackTrace();
        }
    }
}
