package re.btvn.bai6;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CinemaSystem {

    List<TicketPool> rooms = new ArrayList<>();
    List<BookingCounter> counters = new ArrayList<>();

    ExecutorService executor;

    int ticketPrice = 250000;

    public void start(int roomCount, int ticketsPerRoom, int counterCount) {

        rooms.clear();
        counters.clear();

        for (int i = 0; i < roomCount; i++) {
            rooms.add(new TicketPool("" + (char)('A'+i), ticketsPerRoom));
        }

        executor = Executors.newFixedThreadPool(counterCount);

        for (int i = 1; i <= counterCount; i++) {
            BookingCounter c = new BookingCounter("Quầy " + i, rooms);
            counters.add(c);
            executor.submit(c);
        }

        System.out.println("Đã khởi tạo hệ thống với "
                + roomCount + " phòng, "
                + (roomCount*ticketsPerRoom) + " vé, "
                + counterCount + " quầy");
    }

    public void pause() {

        for (BookingCounter c : counters) {
            c.stop();
        }

        System.out.println("Đã tạm dừng tất cả quầy bán vé.");
    }

    public void resume(int counterCount) {

        executor = Executors.newFixedThreadPool(counterCount);

        counters.clear();

        for (int i = 1; i <= counterCount; i++) {
            BookingCounter c = new BookingCounter("Quầy " + i, rooms);
            counters.add(c);
            executor.submit(c);
        }

        System.out.println("Đã tiếp tục hoạt động.");
    }

    public void addTickets(String roomName, int count) {

        for (TicketPool r : rooms) {

            if (r.roomName.equals(roomName)) {
                r.totalTickets += count;
                System.out.println("Đã thêm " + count + " vé vào phòng " + roomName);
            }
        }
    }

    public void stats() {

        System.out.println("\n=== THỐNG KÊ HIỆN TẠI ===");

        int totalSold = 0;

        for (TicketPool r : rooms) {

            System.out.println("Phòng " + r.roomName + ": Đã bán "
                    + r.getSold() + "/" + r.totalTickets + " vé");

            totalSold += r.getSold();
        }

        int revenue = totalSold * ticketPrice;

        System.out.println("Tổng doanh thu: " + revenue + " VNĐ\n");
    }

    public void stopSystem() {

        if (executor != null) {
            executor.shutdownNow();
        }

        System.out.println("Đang dừng hệ thống...");
    }
}