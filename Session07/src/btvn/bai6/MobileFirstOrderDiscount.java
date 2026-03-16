package btvn.bai6;

public class MobileFirstOrderDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {
        System.out.println("Áp dụng giảm giá 15% cho lần đầu");
        return total * 0.15;
    }
}
