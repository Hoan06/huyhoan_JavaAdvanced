package btvn.bai4;

public class Main {
    public static void main(String[] args) {
        OrderRepository repo = new FileOrderRepository();
        NotificationService notify = new EmailService();

        OrderService service = new OrderService(repo, notify);

        Order o1 = new Order("ORD01", 500000);
        service.save(o1, "user@gmail.com");

        service.showOrder();

    }
}
