package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/session14_test";
    private static final String USER = "root";
    private static final String PASSWORD = "13032006";

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Kết nối thất bại");
            throw new RuntimeException(e);
        }
    }
}
