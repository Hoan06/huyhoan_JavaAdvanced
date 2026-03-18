package btth.singleton;

public class HardwareConnection {
    private static HardwareConnection instance;
    private HardwareConnection() {
    }

    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Đã kết nối");
    }

    public void close() {
        System.out.println("Ngắt kết nối");
    }

}
