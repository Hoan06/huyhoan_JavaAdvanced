package btvn.bai4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportBloodTestResults {

    /*
    Phân tích:

    Khi dùng Statement và nối chuỗi SQL bên trong vòng lặp để insert 1.000 bản ghi,
    Database Server bị lãng phí tài nguyên rất lớn vì với mỗi câu lệnh nó phải:

    1. Nhận câu SQL mới.
    2. Phân tích cú pháp (Parse).
    3. Kiểm tra tính hợp lệ.
    4. Tạo hoặc tính toán kế hoạch thực thi (Execution Plan).
    5. Thực thi câu lệnh.

    Dù cấu trúc câu lệnh giống hệt nhau, chỉ khác dữ liệu đầu vào,
    database vẫn phải xử lý lại nhiều lần nếu mỗi vòng lặp tạo ra một câu SQL mới.

    Hậu quả:
    - Tăng tải CPU cho Database Server.
    - Tốn thời gian parse và lập execution plan lặp đi lặp lại.
    - Giảm hiệu năng rõ rệt khi nạp hàng loạt dữ liệu lớn.
    - Hệ thống chậm, đặc biệt khi nhập hàng ngàn kết quả xét nghiệm.

    PreparedStatement tối ưu hơn vì:
    - Câu SQL được chuẩn bị trước một lần.
    - Cấu trúc câu lệnh được biên dịch trước (pre-compiled).
    - Trong vòng lặp chỉ thay đổi giá trị tham số rồi execute.

    => Nhờ đó giảm rất nhiều chi phí parse + execution plan, tốc độ nhanh hơn rõ rệt
    so với Statement dùng nối chuỗi.
    */

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "root";
        String password = "123456";

        List<BloodTestResult> results = new ArrayList<>();

        results.add(new BloodTestResult(1, "Glucose", 5.6));
        results.add(new BloodTestResult(2, "Hemoglobin", 13.2));
        results.add(new BloodTestResult(3, "Cholesterol", 4.8));

        String sql = "INSERT INTO blood_test_results (patient_id, test_name, result_value) VALUES (?, ?, ?)";

        long startTime = System.currentTimeMillis();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (BloodTestResult result : results) {
                pstmt.setInt(1, result.getPatientId());
                pstmt.setString(2, result.getTestName());
                pstmt.setDouble(3, result.getResultValue());

                pstmt.executeUpdate();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Nạp dữ liệu thành công!");
            System.out.println("Thời gian thực thi: " + (endTime - startTime) + " ms");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

