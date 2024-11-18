package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionTest {
    public static void main(String[] args) {
        // Oracle 데이터베이스 연결 정보
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL (xe: Express Edition 기준)
        String user = "your_username"; // Oracle 사용자명
        String password = "your_password"; // Oracle 비밀번호

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Oracle 데이터베이스 연결 성공!");
            } else {
                System.out.println("Oracle 데이터베이스 연결 실패!");
            }
        } catch (Exception e) {
            System.out.println("Oracle 데이터베이스 연결 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
