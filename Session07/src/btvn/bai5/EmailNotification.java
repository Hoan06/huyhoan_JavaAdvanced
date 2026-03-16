package btvn.bai5;

public class EmailNotification implements NotificationService {

    public void send(String message, Customer customer) {
        System.out.println("Đã gửi email xác nhận");
    }
}
