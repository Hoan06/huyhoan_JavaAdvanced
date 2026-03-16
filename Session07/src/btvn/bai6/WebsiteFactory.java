package btvn.bai6;

public class WebsiteFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new WebsiteDiscount();
    }

    public PaymentMethod createPayment() {
        return new WebsiteCreditCardPayment();
    }

    public NotificationService createNotification() {
        return new EmailNotification();
    }
}
