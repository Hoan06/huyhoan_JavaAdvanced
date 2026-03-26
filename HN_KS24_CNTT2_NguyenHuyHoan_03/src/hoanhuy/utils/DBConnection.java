package hoanhuy.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/project_java?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "13032006";

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initDB(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            content = content.replaceAll("(?m)^--.*", "");

            String[] queries = content.split(";");

            try (Connection conn = DBConnection.openConnection();
                 Statement stmt = conn.createStatement()) {

                conn.setAutoCommit(false);

                for (String query : queries) {
                    String sql = query.trim();
                    if (sql.isEmpty()) continue;

                    stmt.execute(sql);
                }

                conn.commit();
                System.out.println("Init DB thành công!");

            } catch (Exception e) {
                System.err.println("Lỗi SQL: " + e.getMessage());
                throw e;
            }

        } catch (Exception e) {
            System.err.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    static void main(String[] args) {
        Connection conn = openConnection();
        initDB("src/hoanhuy/script.sql");
    }
}
