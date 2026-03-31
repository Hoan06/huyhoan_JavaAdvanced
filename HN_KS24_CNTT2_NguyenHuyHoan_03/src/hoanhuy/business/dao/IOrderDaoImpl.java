package hoanhuy.business.dao;

import hoanhuy.model.entity.Order;
import hoanhuy.utils.Color;
import hoanhuy.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IOrderDaoImpl implements IOrderDao {
    private Order mapToOrder(ResultSet rs) throws SQLException {
        return new Order(
          rs.getInt("id"),
          rs.getInt("customer_id"),
          rs.getInt("table_id"),
          rs.getBoolean("isPay"),
          rs.getTimestamp("created_at")
        );
    }

    @Override
    public List<Order> findAll() {
        String sql = "select id , customer_id , table_id , isPay , created_at  from orders";
        List<Order> list = new ArrayList<>();
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = mapToOrder(rs);
                list.add(order);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return  null;
        }
    }

    @Override
    public boolean insert(Order order) {
        String sql = "insert into orders(customer_id, table_id, isPay) values(?,?,?)";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, order.getCustomerId());
            ps.setInt(2, order.getTableId());
            ps.setBoolean(3, order.isPay());
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "select id , customer_id , table_id , isPay , created_at  from orders where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapToOrder(rs);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }

    @Override
    public Order findByTable(int idTable) {
        String sql = "select id , customer_id , table_id , isPay , created_at  from orders where table_id = ? and isPay = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idTable);
            ps.setBoolean(2, false);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapToOrder(rs);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }

    @Override
    public Order findByCustomerId(int idCus) {
        String sql = "select id , customer_id , table_id , isPay , created_at  from orders where customer_id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idCus);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapToOrder(rs);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }

    @Override
    public boolean updateIsPay(int orderId) {
        String sql = "update orders set isPay = ? where id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setBoolean(1, true);
            ps.setInt(2, orderId);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }
}
