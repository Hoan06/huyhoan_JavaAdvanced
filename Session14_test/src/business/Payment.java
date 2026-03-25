package business;

import java.sql.*;

public class Payment implements IPayment {

    @Override
    public void pay(String accountIdSend, String accountIdReceive , double amount) {
        Connection conn = null;
        try {
            conn = DBConnection.openConnection();
            conn.setAutoCommit(false);

            String sql = "select * from Accounts where AccountId = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountIdSend);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.print("Tài khoản gửi không tồn tại !");
                conn.rollback();
            }

            double balance = rs.getDouble("Balance");

            if (balance < amount) {
                System.out.println("Số dư không đủ!");
                conn.rollback();
                return;
            }

            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setString(1, accountIdReceive);

            ResultSet rs2 = ps2.executeQuery();
            if (!rs2.next()) {
                System.out.print("Tài khoản nhận không tồn tại !");
                conn.rollback();
            }

            CallableStatement cs = conn.prepareCall("{call sp_UpdateBalance(?,?)}");
            cs.setString(1, accountIdReceive);
            cs.setDouble(2, amount);


            cs.execute();

            String sql2 = "update Accounts set Balance = Balance - ? where AccountId = ?";
            PreparedStatement ps3 = conn.prepareStatement(sql2);
            ps3.setDouble(1, amount);
            ps3.setString(2, accountIdSend);

            ps3.executeUpdate();

            System.out.println("Chuyển khoản thành công !");
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    static void main() {
        IPayment pay = new Payment();
        pay.pay("ACC01", "ACC02", 100);
        pay.pay("ACC01", "ACC02", 6000);
    }
}
