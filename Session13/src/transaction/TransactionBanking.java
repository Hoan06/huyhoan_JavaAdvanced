package transaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionBanking {
    public static void sendMoney() throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.openConnection();
            // bắt đầu transaction
            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); chọn từ 1-4 theo các cấp độ
            // Trừ tiền tk ông A
            PreparedStatement pre = conn.prepareStatement("update account set balance = balance - ? where id = ?");
            pre.setBigDecimal(1, BigDecimal.valueOf(100));
            pre.setString(2, "1233232");
            int count1 = pre.executeUpdate();
            pre = conn.prepareStatement("update account set balance = balance + ? where id = ?");
            pre.setBigDecimal(1, BigDecimal.valueOf(100));
            pre.setString(2, "1233232");
            int count2 = pre.executeUpdate();
            if (count1 <= 0 || count2 <= 0) {
                conn.rollback();
            } else {
                conn.commit();
            }


        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
