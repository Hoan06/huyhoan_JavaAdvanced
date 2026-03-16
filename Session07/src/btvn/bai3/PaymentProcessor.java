package btvn.bai3;

public class PaymentProcessor implements PaymentMethod {
    PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void pay(double amount) {
        paymentMethod.pay(amount);
    }
}
