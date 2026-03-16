package btvn.bai1;

public class Main {

    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer = new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");
        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001", customer);
        order.addItem(p1, 1);
        order.addItem(p2, 2);

        System.out.println("Đơn hàng ORD001 được tạo");

        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);

        System.out.println("Tổng tiền: " + total);

        OrderRepository repository = new OrderRepository();
        repository.save(order);

        EmailService emailService = new EmailService();
        emailService.sendEmail(customer, "Đơn hàng ORD001 đã được tạo");
    }
}