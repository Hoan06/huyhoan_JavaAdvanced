package btvn.bai2;

public class FixedDiscount implements DiscountStrategy {
    double percent;

    public FixedDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - totalAmount * percent / 100;
    }
}
