package btvn.bai5;

public class InvoiceGenerator {

    public void printInvoice(Order order, double discount) {

        System.out.println("=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.customer.name);

        for (OrderItem i : order.items) {
            System.out.println(i.product.name +
                    " - SL: " + i.quantity +
                    " - Đơn giá: " + i.product.price +
                    " - Thành tiền: " + i.getTotal());
        }

        double total = order.getTotal();
        System.out.println("Tổng tiền: " + total);
        System.out.println("Giảm giá: " + discount);
        System.out.println("Cần thanh toán: " + (total - discount));
    }
}
