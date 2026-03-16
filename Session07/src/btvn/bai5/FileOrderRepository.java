package btvn.bai5;

import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository {

    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}
