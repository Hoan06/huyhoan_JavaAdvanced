package hoanhuy.business.service;

import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemsService {
    List<OrderItem> getAllOrderItems(int orderId);
    List<OrderItem> findAllOfChef();
    void orderFood(int orderId, int foodId , int quantity);
    int findOrderId(Account account);
    void cancelOrderItems();
    void updateStatusTrue(int idOrderItems , int idFoods , int quantity);
    void updateStatusFalse(int idOrderItems);
    OrderItem findOrderItemById(int idOrderItems);
    List<OrderItem> findAllByOrderIdManager(int idOrderItems);
    void updateStatusByChef();
    void deleteOrderItemById(int idOrderItems);
}
