package storedProcedure;

import prepareStatement.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallableStatementDemo {
    static void main(String[] args) throws SQLException {
        try (
                Connection conn = DBConnection.openConnection();
                CallableStatement call = conn.prepareCall("{call delStudentById(?)}")
        ){
            call.setInt(1, 1);
            int count = call.executeUpdate();
            System.out.println(count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
