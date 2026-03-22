package btvn.bai2;

import btvn.bai1.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bai2 {
    public void printAllMedicines() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "select name , quantity from medicine";

        try {
            con = DBContext.openConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            boolean checkData = false;
            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                System.out.println(name + " " + quantity);
                checkData = true;
            }

            if (!checkData) {
                System.out.println("Danh sách rỗng !");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBContext.closeConnection(con, stmt, rs);
        }
    }
}
