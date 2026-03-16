package btvn.bai6;

public class WebsiteCreditCardPayment implements PaymentMethod {

    public void pay(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng thanh toán online");
    }
}
