package btvn.bai3;

public class CODPayment implements CODPayable{
    @Override
    public void pay(double amount) {
        System.out.println("Xử lí thanh toán COD : " + amount + " thành công.");
    }
}
