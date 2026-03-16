package btvn.bai5;

class HolidayDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {
        return total * 0.15;
    }

    public String getName() {
        return "Holiday 15%";
    }
}
