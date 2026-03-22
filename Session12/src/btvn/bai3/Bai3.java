package btvn.bai3;


import btvn.bai1.DBContext;

import java.sql.Connection;
import java.sql.Statement;

public class Bai3 {
    public void updateBedStatus(String bedId) {
        Connection con = null;
        Statement stmt = null;

        String sql = "update bed set bed_status = 'Đang sử dụng' where bed_id = '" + bedId + "'";

        try {
            con = DBContext.openConnection();
            stmt = con.createStatement();

            int rowAffected = stmt.executeUpdate(sql);

            if (rowAffected > 0) {
                System.out.println("Cập nhật trạng thái giường thành công !");
            } else {
                System.out.println("Mã giường này không tồn tại !");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBContext.closeConnection(con, stmt, null);
        }
    }
}