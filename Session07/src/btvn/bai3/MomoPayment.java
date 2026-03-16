package btvn.bai3;

public class MomoPayment implements EWalletPayable{
    @Override
    public void pay(double amount) {
        System.out.println("Xử lí thanh toán MoMo : " + amount + " thành công.");
    }
}
