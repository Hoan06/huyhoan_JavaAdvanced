package btvn.bai6;

public class MemberDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount * 0.05;
    }
}