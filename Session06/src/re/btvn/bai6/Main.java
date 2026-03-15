package re.btvn.bai6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CinemaSystem system = new CinemaSystem();

        while (true) {

            System.out.println("""
1. Bắt đầu mô phỏng
2. Tạm dừng mô phỏng
3. Tiếp tục mô phỏng
4. Thêm vé vào phòng
5. Xem thống kê
6. Phát hiện deadlock
7. Thoát
""");
            System.out.print("Nhập lựa chọn : ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {

                    System.out.print("Số phòng: ");
                    int rooms = sc.nextInt();

                    System.out.print("Vé/phòng: ");
                    int tickets = sc.nextInt();

                    System.out.print("Số quầy: ");
                    int counters = sc.nextInt();

                    system.start(rooms, tickets, counters);
                }

                case 2 -> system.pause();

                case 3 -> {
                    System.out.print("Số quầy: ");
                    int counters = sc.nextInt();
                    system.resume(counters);
                }

                case 4 -> {

                    System.out.print("Phòng: ");
                    String room = sc.next();

                    System.out.print("Số vé thêm: ");
                    int n = sc.nextInt();

                    system.addTickets(room, n);
                }

                case 5 -> system.stats();

                case 6 -> new DeadlockDetector().run();

                case 7 -> {
                    system.stopSystem();
                    System.exit(0);
                }
            }
        }
    }
}