package btvn.bai1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HardwareConnection connection = HardwareConnection.getInstance();

        Device device = null;

                while (true) {

                    System.out.println("\n===== MENU =====");
                    System.out.println("1. Kết nối phần cứng");
                    System.out.println("2. Tạo thiết bị");
                    System.out.println("3. Bật thiết bị");
                    System.out.println("4. Tắt thiết bị");
                    System.out.println("5. Thoát");
                    System.out.print("Chọn: ");

                    int choice = sc.nextInt();

                    switch (choice) {

                        case 1:
                            connection.connect();
                            break;

                        case 2:
                            System.out.println("Chọn loại thiết bị:");
                            System.out.println("1. Light");
                            System.out.println("2. Fan");
                            System.out.println("3. AirConditioner");

                            int type = sc.nextInt();
                            DeviceFactory factory = null;

                            switch (type) {
                                case 1:
                                    factory = new LightFactory();
                                    break;
                                case 2:
                                    factory = new FanFactory();
                                    break;

                                default:
                                    System.out.println("Loại không hợp lệ");
                                    break;
                            }

                            if (factory != null) {
                                device = factory.createDevice();
                                System.out.println("Đã tạo thiết bị.");
                            }

                            break;

                        case 3:
                            if (device != null) {
                                device.turnOn();
                            } else {
                                System.out.println("Chưa có thiết bị.");
                            }
                            break;

                        case 4:
                            if (device != null) {
                                device.turnOff();
                            } else {
                                System.out.println("Chưa có thiết bị.");
                            }
                            break;

                        case 5:
                            connection.disconnect();
                            System.out.println("Thoát chương trình.");
                            return;

                        default:
                            System.out.println("Lựa chọn không hợp lệ.");
                    }
                }

    }
}
