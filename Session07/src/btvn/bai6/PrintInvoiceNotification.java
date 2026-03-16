package btvn.bai6;

public class PrintInvoiceNotification implements NotificationService {

    public void notifyCustomer(String message) {
        System.out.println("In hóa đơn giấy: " + message);
    }
}
