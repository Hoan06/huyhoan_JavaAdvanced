package hoanhuy.business.dao;

import hoanhuy.model.entity.OrderItem;
import hoanhuy.model.entity.OrderItemStatus;
import hoanhuy.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IOrderItemsDaoImpl implements IOrderItemsDao {
    private OrderItem mapToOrderItem(ResultSet rs) throws SQLException {
        return new OrderItem(
                rs.getInt("id"),
                rs.getInt("order_id"),
                rs.getInt("menu_item_id"),
                rs.getInt("quantity"),
                OrderItemStatus.valueOf(rs.getString("status"))
        );
    }

    @Override
    public List<OrderItem> findAllByOrderId(int idOrder) {
        String sql = "select id , order_id , menu_item_id , quantity , status from order_items where order_id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idOrder);
            ResultSet rs = ps.executeQuery();
            List<OrderItem> orderItems = new ArrayList<>();
            while(rs.next()){
                OrderItem orderItem = mapToOrderItem(rs);
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(OrderItem orderItem) {
        String sql = "insert into order_items(order_id,menu_item_id,quantity,status) values(?,?,?,?)";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuItemId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setString(4, orderItem.getStatus().name());
            int count =  ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(int idOrderItems, String newStatus) {
        String sql = "update order_items set status = ? where id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, newStatus);
            ps.setInt(2, idOrderItems);
            int count =  ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int idOrderItems) {
        String sql = "delete from order_items where id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idOrderItems);
            int count =  ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderItem findOrderItemsById(int idOrderItems) {
        String sql = "select id , order_id , menu_item_id , quantity , status from order_items where id = ? ";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idOrderItems);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapToOrderItem(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
