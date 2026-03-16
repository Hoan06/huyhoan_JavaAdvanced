package btvn.bai5;

public class OrderService {

    private OrderRepository repository;
    private NotificationService notification;

    public OrderService(OrderRepository repository,
                        NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(Order order,
                            DiscountStrategy discount,
                            PaymentMethod payment) {

        double total = order.getTotal();
        double discountValue = discount.applyDiscount(total);
        double finalAmount = total - discountValue;

        InvoiceGenerator invoice = new InvoiceGenerator();
        invoice.printInvoice(order, discountValue);

        payment.pay(finalAmount);

        repository.save(order);

        notification.send("Đơn hàng thành công", order.customer);
    }

    public double getRevenue() {
        double total = 0;
        for (Order o : repository.findAll()) {
            total += o.getTotal();
        }
        return total;
    }
}
