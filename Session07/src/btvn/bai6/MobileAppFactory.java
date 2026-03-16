package btvn.bai6;

public class MobileAppFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new MobileFirstOrderDiscount();
    }

    public PaymentMethod createPayment() {
        return new MomoPayment();
    }

    public NotificationService createNotification() {
        return new PushNotification();
    }
}
