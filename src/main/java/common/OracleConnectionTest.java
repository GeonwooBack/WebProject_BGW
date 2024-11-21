package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle URL (변경 필요)
        String user = "webproject_db"; // Oracle 사용자 이름
        String password = "1234"; // Oracle 비밀번호
        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Oracle 데이터베이스 연결 성공!");
            } else {
                System.out.println("Oracle 데이터베이스 연결 실패!");
            }

            conn.close(); // 연결 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
