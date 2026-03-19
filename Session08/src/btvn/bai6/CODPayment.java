package btvn.bai6;

public class CODPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán khi nhận hàng: " + amount);
    }
}