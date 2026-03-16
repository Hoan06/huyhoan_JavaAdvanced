package btvn.bai5;

public interface DiscountStrategy {
    double applyDiscount(double total);
    String getName();
}