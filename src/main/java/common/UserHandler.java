package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserHandler {
    public boolean registerUser(String username, String password, String name, String email, String phoneNumber, String gender, String birthdate) {
        String sql = "INSERT INTO users (username, password, name, email, phone_number, gender, birthdate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, gender);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(birthdate);
            pstmt.setDate(7, new java.sql.Date(date.getTime()));

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
        }
        return false;
    }
}
