package re.btvn.bai6;

import java.util.List;
import java.util.Random;

public class BookingCounter implements Runnable {

    String name;
    List<TicketPool> rooms;
    boolean running = true;

    Random random = new Random();

    public BookingCounter(String name, List<TicketPool> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        System.out.println(name + " bắt đầu bán vé...");

        while (running) {

            TicketPool room = rooms.get(random.nextInt(rooms.size()));

            if (room.sellTicket()) {
                System.out.println(name + " bán vé phòng " + room.roomName);
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }
}