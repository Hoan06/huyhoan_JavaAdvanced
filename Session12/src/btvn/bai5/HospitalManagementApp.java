package btvn.bai5;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementApp {

    private static final String URL = "jdbc:mysql://localhost:3306/rikkei_hospital_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ BỆNH NHÂN =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & Tính phí");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showPatients();
                    break;
                case 2:
                    addPatient(scanner);
                    break;
                case 3:
                    updateMedicalRecord(scanner);
                    break;
                case 4:
                    dischargePatient(scanner);
                    break;
                case 5:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void showPatients() {
        String sql = "SELECT id, name, age, department FROM patients";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- DANH SÁCH BỆNH NHÂN ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("department")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPatient(Scanner scanner) {
        String sql = "INSERT INTO patients (name, age, department) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.print("Nhập tên bệnh nhân: ");
            String name = scanner.nextLine();

            System.out.print("Nhập tuổi: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nhập khoa: ");
            String department = scanner.nextLine();

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);

            pstmt.executeUpdate();

            System.out.println("Thêm bệnh nhân thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMedicalRecord(Scanner scanner) {
        String sql = "UPDATE patients SET disease = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nhập bệnh lý mới: ");
            String disease = scanner.nextLine();

            pstmt.setString(1, disease);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dischargePatient(Scanner scanner) {
        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = scanner.nextInt();

            cstmt.setInt(1, id);

            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            double totalFee = cstmt.getDouble(2);

            System.out.println("Tổng viện phí: " + totalFee);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}