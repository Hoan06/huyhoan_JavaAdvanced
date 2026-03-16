package btvn.bai1;

public class OrderCalculator {

    public double calculateTotal(Order order) {
        double total = 0;

        for (OrderItem item : order.items) {
            total += item.product.price * item.quantity;
        }

        return total;
    }
}