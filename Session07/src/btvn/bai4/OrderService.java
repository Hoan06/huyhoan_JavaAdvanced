package btvn.bai4;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void save(Order order , String recipient){
        orderRepository.save(order);
        notificationService.send("Đơn hàng đã được tạo " , recipient);
    }

    public void showOrder(){
        List<Order> orderList = orderRepository.findAll();
        for(Order order : orderList){
            System.out.println(order);
        }
    }
}
