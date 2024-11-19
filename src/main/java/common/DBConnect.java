package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 본인 DB에 맞게 수정
    private static final String USER = "webproject_db"; // 사용자명
    private static final String PASSWORD = "1234"; // 비밀번호

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("JDBC 드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("DB 연결 실패: " + e.getMessage());
            return null;
        }
    }
}
