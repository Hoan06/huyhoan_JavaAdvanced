package btvn.bai1;
import java.util.ArrayList;
import java.util.List;

public class Order {
    String orderId;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }
}