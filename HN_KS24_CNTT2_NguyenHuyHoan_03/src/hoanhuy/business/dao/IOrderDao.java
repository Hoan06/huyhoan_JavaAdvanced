package hoanhuy.business.dao;

import hoanhuy.model.entity.Order;

import java.util.List;

public interface IOrderDao {
    List<Order> findAll();
    boolean insert(Order order);
    Order findById(int id);
    Order findByTable(int idTable);
    Order findByCustomerId(int idCus);
}
