package btvn.bai5;

class PercentageDiscount implements DiscountStrategy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double applyDiscount(double total) {
        return total * percent;
    }

    public String getName() {
        return percent * 100 + "%";
    }
}
