package btvn.bai3;

import java.sql.*;
import java.math.BigDecimal;

public class GetSurgeryFee {

    /*
    Phân tích:

    JDBC bắt buộc phải gọi registerOutParameter() trước khi thực thi Stored Procedure
    vì driver cần biết trước tham số nào là OUT và kiểu dữ liệu của nó để chuẩn bị
    vùng nhận kết quả trả về từ database.

    Nếu không đăng ký trước:
    - JDBC không biết tham số nào sẽ trả kết quả ra.
    - Khi gọi getBigDecimal(), getDouble()... sẽ dễ gây lỗi hoặc không lấy được giá trị.

    Ngoài ra, chỉ số tham số trong CallableStatement được tính bắt đầu từ 1
    theo đúng thứ tự xuất hiện trong câu lệnh gọi thủ tục.

    Với tham số đầu ra có kiểu DECIMAL trong SQL, trong Java phải đăng ký bằng:
    Types.DECIMAL

    => Kết luận:
    - Phải registerOutParameter() trước execute().
    - Phải đăng ký đúng vị trí tham số và đúng kiểu dữ liệu SQL tương ứng.
    */

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/rikkei_hospital";
        String user = "root";
        String password = "123456";

        int surgeryId = 101;

        String sql = "{CALL GET_SURGERY_FEE(?, ?)}";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, surgeryId);

            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            BigDecimal totalCost = cstmt.getBigDecimal(2);

            System.out.println("Chi phí phẫu thuật là: " + totalCost);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}