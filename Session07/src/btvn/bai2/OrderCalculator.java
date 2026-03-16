package btvn.bai2;

public class OrderCalculator {
    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double applyDiscount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}
