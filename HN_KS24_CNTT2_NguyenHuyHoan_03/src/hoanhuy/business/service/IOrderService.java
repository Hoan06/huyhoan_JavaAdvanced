package hoanhuy.business.service;

import hoanhuy.model.entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    void orderTable(int idCus);
    Order findByTable(int idTable);
    void updateIsPay(int orderId);
}
