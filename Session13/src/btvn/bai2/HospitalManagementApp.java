package btvn.bai2;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementApp {

    private static final String URL = "jdbc:mysql://localhost:3306/rikkei_hospital_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void payInvoice(Scanner scanner) {
        String sql1 = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ? AND balance >= ?";
        String sql2 = "UPDATE Invoices SET status = 'Đã thanh toán' WHERE invoice_id = ?";

        System.out.print("Nhập ID bệnh nhân: ");
        int patientId = scanner.nextInt();

        System.out.print("Nhập ID hóa đơn: ");
        int invoiceId = scanner.nextInt();

        System.out.print("Nhập số tiền cần thanh toán: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            /*
             * PHẦN 1 - PHÂN TÍCH:
             * Khi setAutoCommit(false), các câu lệnh thuộc cùng một transaction.
             * Nếu trừ tiền ví thành công nhưng cập nhật hóa đơn bị lỗi,
             * mà chỉ in lỗi ra bằng System.out.println() thì transaction vẫn đang dang dở.
             * Không rollback sẽ khiến connection bị treo và dữ liệu không được khôi phục.
             * Vì vậy cần gọi rollback() để hủy toàn bộ thay đổi khi có lỗi.
             */

            conn.setAutoCommit(false);

            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setDouble(1, amount);
            pstmt1.setInt(2, patientId);
            pstmt1.setDouble(3, amount);
            int r1 = pstmt1.executeUpdate();

            if (r1 <= 0) {
                throw new SQLException("Không đủ tiền hoặc không tìm thấy ví.");
            }

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, invoiceId);
            int r2 = pstmt2.executeUpdate();

            if (r2 <= 0) {
                throw new SQLException("Không cập nhật được hóa đơn.");
            }

            conn.commit();
            System.out.println("Thanh toán thành công!");

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi: " + e.getMessage());

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