package btvn.bai1;

public class OrderRepository {

    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.orderId);
    }
}
