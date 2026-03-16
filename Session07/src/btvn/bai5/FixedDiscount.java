package btvn.bai5;

class FixedDiscount implements DiscountStrategy {

    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    public double applyDiscount(double total) {
        return amount;
    }

    public String getName() {
        return "Fixed " + amount;
    }
}