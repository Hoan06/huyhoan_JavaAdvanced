package btvn.bai5;

public class SMSNotification implements NotificationService {

    public void send(String message, Customer customer) {
        System.out.println("Đã gửi SMS xác nhận");
    }
}
