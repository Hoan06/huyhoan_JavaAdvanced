package btvn.bai6;

public class PushNotification implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("Gửi push notification: " + message);
    }
}
