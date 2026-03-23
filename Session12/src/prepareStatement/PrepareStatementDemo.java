package prepareStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementDemo {
    // Kế thừa statement
    // Cho phép truyền tham số vào sql thông qua setter

    static void main() {
        // Mở kết nối
        try (Connection conn = DBConnection.openConnection();
        // Chuẩn bị câu lệnh
        PreparedStatement pre = conn.prepareStatement("select * from student where id = ?");
        ){
            // truyền tham số nếu có
            pre.setInt(1,2);
            // thực thi câu lệnh : excuteQuery
            ResultSet rs = pre.executeQuery();

            // Xử lí kết quả trả về nếu có
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean insert(String id , String name , int age , double salary){
        String sql = "insert into student values (?,?,?,?)";

        try (
             Connection conn = DBConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setInt(3,age);
            ps.setDouble(4,salary);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(String id , String name ){
        String sql = "update student set name = ? where id = ?";

        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1,name);
            ps.setString(2,id);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
