package btvn.bai3;

public class Main {
    public static void main(String[] args) {
        PaymentMethod cod = new CODPayment();
        PaymentProcessor processor1 = new PaymentProcessor(cod);
        processor1.pay(500000);

        PaymentMethod card = new CreditCardPayment();
        PaymentProcessor processor2 = new PaymentProcessor(card);
        processor2.pay(1000000);

        PaymentMethod momo = new MomoPayment();
        PaymentProcessor processor3 = new PaymentProcessor(momo);
        processor3.pay(750000);

        // Kiểm tra LSP
        PaymentMethod test = new CreditCardPayment();
        PaymentProcessor processor4 = new PaymentProcessor(test);
        processor4.pay(1000000);
    }
}
