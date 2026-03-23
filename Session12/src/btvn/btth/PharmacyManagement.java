package btvn.btth;

import java.sql.*;
import java.util.Scanner;
import java.math.BigDecimal;

public class PharmacyManagement {

    private static final String URL = "jdbc:mysql://localhost:3306/pharmacy_btth";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void updateMedicineStock(int id, int addedQuantity) {
        String sql = "UPDATE medicines SET stock = stock + ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, addedQuantity);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật tồn kho thành công!");
            } else {
                System.out.println("Không tìm thấy thuốc!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findMedicinesByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM medicines WHERE price BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- DANH SÁCH THUỐC ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getBigDecimal("price") + " | " +
                                rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void calculatePrescriptionTotal(int prescriptionId) {
        String sql = "{CALL CalculatePrescriptionTotal(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, prescriptionId);
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            BigDecimal total = cstmt.getBigDecimal(2);

            System.out.println("Tổng tiền đơn thuốc: " + total);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getDailyRevenue(String dateInput) {
        String sql = "{CALL GetDailyRevenue(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            Date date = Date.valueOf(dateInput);

            cstmt.setDate(1, date);
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            BigDecimal revenue = cstmt.getBigDecimal(2);

            System.out.println("Doanh thu ngày " + dateInput + ": " + revenue);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ NHÀ THUỐC =====");
            System.out.println("1. Cập nhật tồn kho");
            System.out.println("2. Tìm thuốc theo giá");
            System.out.println("3. Tính tiền đơn thuốc");
            System.out.println("4. Doanh thu theo ngày");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID thuốc: ");
                    int id = scanner.nextInt();
                    System.out.print("Nhập số lượng thêm: ");
                    int qty = scanner.nextInt();
                    updateMedicineStock(id, qty);
                    break;

                case 2:
                    System.out.print("Giá min: ");
                    double min = scanner.nextDouble();
                    System.out.print("Giá max: ");
                    double max = scanner.nextDouble();
                    findMedicinesByPriceRange(min, max);
                    break;

                case 3:
                    System.out.print("Nhập ID đơn thuốc: ");
                    int pid = scanner.nextInt();
                    calculatePrescriptionTotal(pid);
                    break;

                case 4:
                    System.out.print("Nhập ngày (yyyy-MM-dd): ");
                    String date = scanner.nextLine();
                    getDailyRevenue(date);
                    break;

                case 5:
                    System.out.println("Thoát!");
                    return;

                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }
}