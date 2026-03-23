package btvn.bai2;

import java.sql.*;

public class UpdateVitalSigns {

            /*
        Phân tích:

        PreparedStatement giúp tránh lỗi định dạng số (dấu chấm / dấu phẩy) vì nó xử lý dữ liệu
        theo kiểu (type-safe), không phải dạng chuỗi.

        Cụ thể:
        - Khi dùng Statement: lập trình viên phải nối chuỗi SQL thủ công.
          Ví dụ: "UPDATE ... SET temperature = " + temp
          => Nếu temp = 37,5 (locale Pháp/Việt), SQL sẽ lỗi cú pháp.

        - Khi dùng PreparedStatement:
          + Dùng setDouble(), setInt() để truyền dữ liệu.
          + Driver JDBC sẽ tự động chuyển đổi giá trị sang định dạng chuẩn của database (dấu chấm).
          + Không phụ thuộc vào Locale của hệ điều hành.

        Lợi ích:
        - Tránh lỗi cú pháp do khác biệt dấu phẩy / dấu chấm.
        - Đảm bảo đúng kiểu dữ liệu (double, int).
        - Tăng độ an toàn và ổn định của hệ thống.

        => PreparedStatement giúp xử lý dữ liệu theo kiểu thay vì chuỗi, nên không bị lỗi định dạng số.
        */

    public static void main(String[] args) {

        int patientId = 1;
        double temperature = 37.5;
        int heartRate = 80;

        String url = "jdbc:mysql://localhost:3306/rikkei_hospital";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "UPDATE patients SET temperature = ?, heart_rate = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, temperature);
            pstmt.setInt(2, heartRate);
            pstmt.setInt(3, patientId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
