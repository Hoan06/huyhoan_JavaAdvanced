package btvn.bai5;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOrderRepository implements OrderRepository {

    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu DB: " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}
