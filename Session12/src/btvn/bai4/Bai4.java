package btvn.bai4;


import btvn.bai1.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bai4 {
    public void findPatientByName(String keyword) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // lọc dữ liệu đầu vào
            if (keyword != null) {
                keyword = keyword.replace("--", "");
                keyword = keyword.replace(";", "");
                keyword = keyword.replace("'", "");
            }

            String sql = "select * from patient where name = '" + keyword + "'";

            con = DBContext.openConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            boolean checkData = false;
            while (rs.next()) {
                String id = rs.getString("patient_id");
                String name = rs.getString("name");

                System.out.println(id + " - " + name);
                checkData = true;
            }

            if (!checkData) {
                System.out.println("Không tìm thấy bệnh nhân !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBContext.closeConnection(con, stmt, rs);
        }
    }
}