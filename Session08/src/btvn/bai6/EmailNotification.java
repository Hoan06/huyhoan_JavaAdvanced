package btvn.bai6;

public class EmailNotification implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("Gửi email: " + message);
    }
}
