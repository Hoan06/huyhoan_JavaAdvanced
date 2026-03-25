package btvn.bai3;

import java.sql.*;

public class HospitalManagementApp {

    private static final String URL = "jdbc:mysql://localhost:3306/rikkei_hospital_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        xuatVienVaThanhToan(1, 500000);
    }

    public static void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        PreparedStatement psSelect = null;
        PreparedStatement psUpdateBalance = null;
        PreparedStatement psUpdateBed = null;
        PreparedStatement psUpdatePatient = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);

            String selectPatientSql = "SELECT advance_balance, bed_id FROM patients WHERE id = ?";
            psSelect = conn.prepareStatement(selectPatientSql);
            psSelect.setInt(1, maBenhNhan);
            rs = psSelect.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Không tìm thấy bệnh nhân với mã: " + maBenhNhan);
            }

            double soDuTamUng = rs.getDouble("advance_balance");
            int bedId = rs.getInt("bed_id");

            /*
             * BẪY 1 - BẪY LOGIC NGHIỆP VỤ (THIẾU TIỀN)
             * Trước khi trừ tiền, phải kiểm tra số dư tạm ứng hiện tại của bệnh nhân.
             * Nếu số dư nhỏ hơn tiền viện phí thì không được phép tiếp tục,
             * vì sẽ làm số dư bị âm và sai logic nghiệp vụ.
             * Trong trường hợp này phải chủ động ném ra exception để transaction rollback.
             */
            if (soDuTamUng < tienVienPhi) {
                throw new SQLException("Số dư tạm ứng không đủ để thanh toán viện phí.");
            }

            String updateBalanceSql = "UPDATE patients SET advance_balance = advance_balance - ? WHERE id = ?";
            psUpdateBalance = conn.prepareStatement(updateBalanceSql);
            psUpdateBalance.setDouble(1, tienVienPhi);
            psUpdateBalance.setInt(2, maBenhNhan);
            int rowBalance = psUpdateBalance.executeUpdate();

            /*
             * BẪY 2 - BẪY DỮ LIỆU ẢO (ROW AFFECTED = 0)
             * JDBC không tự ném SQLException nếu executeUpdate() không cập nhật dòng nào.
             * Nếu rowBalance = 0 mà vẫn commit thì transaction trở nên vô nghĩa.
             * Vì vậy phải tự kiểm tra và chủ động ném exception để rollback.
             */
            if (rowBalance == 0) {
                throw new SQLException("Không thể trừ tiền tạm ứng vì không có dòng nào được cập nhật.");
            }

            String updateBedSql = "UPDATE beds SET status = 'Trống' WHERE id = ?";
            psUpdateBed = conn.prepareStatement(updateBedSql);
            psUpdateBed.setInt(1, bedId);
            int rowBed = psUpdateBed.executeUpdate();

            /*
             * BẪY 2 - BẪY DỮ LIỆU ẢO (ROW AFFECTED = 0)
             * Nếu không có giường nào được cập nhật thì nghĩa là dữ liệu liên quan không tồn tại
             * hoặc không khớp. Phải xem đây là lỗi nghiệp vụ và rollback toàn bộ transaction.
             */
            if (rowBed == 0) {
                throw new SQLException("Không thể giải phóng giường bệnh vì không có dòng nào được cập nhật.");
            }

            String updatePatientSql = "UPDATE patients SET status = 'Đã xuất viện' WHERE id = ?";
            psUpdatePatient = conn.prepareStatement(updatePatientSql);
            psUpdatePatient.setInt(1, maBenhNhan);
            int rowPatient = psUpdatePatient.executeUpdate();

            /*
             * BẪY 2 - BẪY DỮ LIỆU ẢO (ROW AFFECTED = 0)
             * Nếu cập nhật trạng thái bệnh nhân trả về 0 dòng bị ảnh hưởng,
             * phải chủ động ném exception để ngăn việc commit sai.
             */
            if (rowPatient == 0) {
                throw new SQLException("Không thể cập nhật trạng thái bệnh nhân xuất viện.");
            }

            conn.commit();
            System.out.println("Xuất viện và thanh toán thành công cho bệnh nhân có mã: " + maBenhNhan);

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback transaction do xảy ra lỗi.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.out.println("Xuất viện thất bại: " + e.getMessage());

        } finally {
            try {
                if (rs != null) rs.close();
                if (psSelect != null) psSelect.close();
                if (psUpdateBalance != null) psUpdateBalance.close();
                if (psUpdateBed != null) psUpdateBed.close();
                if (psUpdatePatient != null) psUpdatePatient.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}