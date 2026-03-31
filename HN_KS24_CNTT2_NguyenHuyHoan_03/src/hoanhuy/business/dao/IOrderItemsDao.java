package hoanhuy.business.dao;

import hoanhuy.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemsDao {
    List<OrderItem> findAllByOrderId(int idOrder);
    List<OrderItem> findAllOfChef();
    boolean insert(OrderItem orderItem);
    boolean update(int idOrderItems , String newStatus);
    boolean delete(int idOrderItems);
    OrderItem findOrderItemsById(int idOrderItems);
    List<OrderItem> findAllByOrderIdManager(int idOrderItems);
}
