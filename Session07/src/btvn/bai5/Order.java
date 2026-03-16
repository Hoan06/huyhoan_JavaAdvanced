package btvn.bai5;

import java.util.ArrayList;
import java.util.List;

class Order {
    String id;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem i : items) {
            total += i.getTotal();
        }
        return total;
    }
}
