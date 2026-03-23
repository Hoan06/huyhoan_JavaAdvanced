package btvn.bai1;

import java.sql.*;

public class DoctorLogin {

            /*
        Phân tích:

        PreparedStatement được coi là "tấm khiên" chống SQL Injection vì nó tách biệt hoàn toàn
        câu lệnh SQL và dữ liệu đầu vào của người dùng.

        Cơ chế hoạt động:
        - Câu lệnh SQL được "biên dịch trước" (pre-compiled) bởi database với các placeholder (?).
        - Sau đó, các giá trị đầu vào được truyền vào thông qua setter (setString, setInt,...).

        Điều này giúp:
        - Dữ liệu người dùng KHÔNG được coi là một phần của câu lệnh SQL.
        - Các ký tự đặc biệt như: ' OR '1'='1 sẽ chỉ được hiểu là chuỗi bình thường,
          không thể thay đổi logic truy vấn.

        Khác với Statement:
        - Statement nối chuỗi trực tiếp -> dễ bị chèn mã SQL độc hại.
        - PreparedStatement dùng tham số -> an toàn tuyệt đối trước SQL Injection.

        => Vì vậy, PreparedStatement ngăn chặn việc "tiêm" mã SQL từ input người dùng.
        */

    public static void main(String[] args) {
        String doctorCode = "hello123";
        String password = "123456789";

        String url = "jdbc:mysql://localhost:3306/rikkei_hospital";
        String user = "root";
        String dbPassword = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, dbPassword)) {

            String sql = "SELECT * FROM doctors WHERE doctor_code = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, doctorCode);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}