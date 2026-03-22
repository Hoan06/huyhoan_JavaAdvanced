package ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/employee_demo";
    private static final String USER = "root";
    private static final String PASS = "13032006";

    public static Connection openConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Lỗi : Kết nối database thất bại !");
            throw new RuntimeException(e);
        }
    }
}
