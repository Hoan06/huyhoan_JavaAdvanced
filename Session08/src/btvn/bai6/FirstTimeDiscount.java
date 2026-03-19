package btvn.bai6;

public class FirstTimeDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount * 0.15;
    }
}
