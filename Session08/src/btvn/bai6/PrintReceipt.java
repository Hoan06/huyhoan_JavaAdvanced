package btvn.bai6;

public class PrintReceipt implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("In hóa đơn: " + message);
    }
}
