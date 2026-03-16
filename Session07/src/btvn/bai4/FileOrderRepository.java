package btvn.bai4;

import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository{

    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào file : " + order.id);
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }
}
