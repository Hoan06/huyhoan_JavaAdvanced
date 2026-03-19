package btvn.bai6;

public class OrderService {
    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {
        this.discount = factory.createDiscountStrategy();
        this.payment = factory.createPaymentMethod();
        this.notification = factory.createNotificationService();
    }

    public void placeOrder(double price, int quantity) {
        double total = price * quantity;

        double discountAmount = discount.applyDiscount(total);
        double finalAmount = total - discountAmount;

        System.out.println("Giảm giá: " + discountAmount);

        payment.pay(finalAmount);

        notification.notifyUser("Đơn hàng thành công");
    }
}