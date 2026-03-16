package btvn.bai6;

public class OrderService {

    private SalesChannelFactory factory;

    public OrderService(SalesChannelFactory factory) {
        this.factory = factory;
    }

    public void createOrder(double price) {

        DiscountStrategy discount = factory.createDiscount();
        PaymentMethod payment = factory.createPayment();
        NotificationService notification = factory.createNotification();

        double discountValue = discount.applyDiscount(price);
        double finalPrice = price - discountValue;

        System.out.println("Tổng tiền: " + price);
        System.out.println("Cần thanh toán: " + finalPrice);

        payment.pay(finalPrice);

        notification.notifyCustomer("Đơn hàng thành công");
    }
}
