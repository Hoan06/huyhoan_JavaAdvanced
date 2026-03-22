package btvn.bai1;

import java.sql.*;

public class DBContext {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "13032006";
    public static Connection openConnection(){
        try {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection con , Statement stmt, ResultSet rs){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {}
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {}
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {}
        }
    }
}
