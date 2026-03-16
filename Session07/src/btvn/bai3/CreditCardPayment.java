package btvn.bai3;

public class CreditCardPayment implements CardPayable{
    @Override
    public void pay(double amount) {
        System.out.println("Xử lí thanh toán thẻ tín dụng : " + amount + " thành công.");
    }
}
