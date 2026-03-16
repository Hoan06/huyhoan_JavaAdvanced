package btvn.bai2;

public class Main {
    public static void main(String[] args) {

        double total = 1000000;

        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        OrderCalculator o1 = new OrderCalculator(new PercentageDiscount(10));
        System.out.println("Số tiền sau giảm: " + o1.applyDiscount(total));


        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        OrderCalculator calc2 = new OrderCalculator(new FixedDiscount(50000));
        System.out.println("Số tiền sau giảm: " + calc2.applyDiscount(total));

        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        OrderCalculator calc3 = new OrderCalculator(new NoDiscount());
        System.out.println("Số tiền sau giảm: " + calc3.applyDiscount(total));

    }
}
