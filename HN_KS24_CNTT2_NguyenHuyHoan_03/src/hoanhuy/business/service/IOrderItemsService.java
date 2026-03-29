package hoanhuy.business.service;

import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemsService {
    List<OrderItem> getAllOrderItems(int orderId);
    void orderFood(int orderId, int foodId , int quantity);
    int findOrderId(Account account);
    void cancelOrderItems();
}
