package re.btvn.bai5;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 6);

        new Thread(new BookingCounter("Quầy 1", roomA, roomB, roomC)).start();
        new Thread(new BookingCounter("Quầy 2", roomA, roomB, roomC)).start();
        new Thread(new BookingCounter("Quầy 3", roomA, roomB, roomC)).start();
        new Thread(new BookingCounter("Quầy 4", roomA, roomB, roomC)).start();
        new Thread(new BookingCounter("Quầy 5", roomA, roomB, roomC)).start();

        new Thread(new TimeoutManager(roomA, roomB, roomC)).start();
    }
}
