package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class OracleLoginExample {
    
    // Oracle 데이터베이스 연결 정보
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // orcl은 서비스 이름
    private static final String USER = "project_db";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // 데이터베이스 연결
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Oracle 데이터베이스 연결 성공!");

            // 사용자로부터 입력 받기
            Scanner scanner = new Scanner(System.in);
            System.out.print("사용자명을 입력하세요: ");
            String username = scanner.nextLine();
            System.out.print("비밀번호를 입력하세요: ");
            String passwordInput = scanner.nextLine();

            // 사용자 인증 SQL
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordInput);

            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("로그인 성공! 환영합니다, " + username + "님.");
            } else {
                System.out.println("로그인 실패: 사용자명 또는 비밀번호가 올바르지 않습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 정리
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

