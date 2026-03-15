package re.btvn.bai6;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketPool {

    String roomName;
    int totalTickets;
    AtomicInteger sold = new AtomicInteger(0);

    public TicketPool(String roomName, int totalTickets) {
        this.roomName = roomName;
        this.totalTickets = totalTickets;
    }

    public synchronized boolean sellTicket() {

        if (sold.get() < totalTickets) {
            sold.incrementAndGet();
            return true;
        }
        return false;
    }

    public int remaining() {
        return totalTickets - sold.get();
    }

    public int getSold() {
        return sold.get();
    }
}
