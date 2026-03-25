package btvn.bai1;

import java.sql.*;
import java.util.Scanner;

public class PharmacyService {

    private static final String URL = "jdbc:mysql://localhost:3306/rikkei_hospital_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void issueMedicine(Scanner scanner) {
        String updateStockSql = "UPDATE medicines SET stock = stock - ? WHERE id = ? AND stock >= ?";
        String insertRecordSql = "INSERT INTO medical_records (patient_id, medicine_id, quantity) VALUES (?, ?, ?)";

        System.out.print("Nhập ID bệnh nhân: ");
        int patientId = scanner.nextInt();

        System.out.print("Nhập ID thuốc: ");
        int medicineId = scanner.nextInt();

        System.out.print("Nhập số lượng thuốc: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            /*
             * PHẦN 1 - PHÂN TÍCH LOGIC:
             * Trong JDBC, mặc định Auto-Commit = true nên mỗi câu lệnh SQL sẽ được commit ngay sau khi thực thi thành công.
             * Vì vậy, nếu câu lệnh trừ thuốc trong kho chạy xong thì dữ liệu đã được lưu lại ngay.
             * Sau đó nếu câu lệnh lưu hồ sơ bệnh án bị lỗi do mạng chập chờn hoặc mất kết nối,
             * thì JDBC không thể tự hủy câu lệnh UPDATE trước đó vì nó đã commit rồi.
             * Kết quả là thuốc đã bị trừ nhưng không có bản ghi hồ sơ tương ứng, làm dữ liệu bị sai lệch.
             * Muốn đảm bảo toàn vẹn dữ liệu thì phải tắt Auto-Commit và gom toàn bộ nghiệp vụ vào cùng một transaction.
             */

            conn.setAutoCommit(false);

            pstmt1 = conn.prepareStatement(updateStockSql);
            pstmt1.setInt(1, quantity);
            pstmt1.setInt(2, medicineId);
            pstmt1.setInt(3, quantity);

            int row1 = pstmt1.executeUpdate();

            if (row1 <= 0) {
                throw new SQLException("Không đủ thuốc hoặc không tìm thấy thuốc.");
            }

            pstmt2 = conn.prepareStatement(insertRecordSql);
            pstmt2.setInt(1, patientId);
            pstmt2.setInt(2, medicineId);
            pstmt2.setInt(3, quantity);

            int row2 = pstmt2.executeUpdate();

            if (row2 <= 0) {
                throw new SQLException("Không thể lưu hồ sơ bệnh án.");
            }

            conn.commit();
            System.out.println("Cấp phát thuốc thành công!");

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
