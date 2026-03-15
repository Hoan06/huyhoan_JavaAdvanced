package re.btvn.bai4;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    String roomName;
    List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int totalTickets) {

        this.roomName = roomName;

        for (int i = 1; i <= totalTickets; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.isSold = true;
                return t;
            }
        }

        return null;
    }

    public int remainingTickets() {

        int count = 0;

        for (Ticket t : tickets) {
            if (!t.isSold) {
                count++;
            }
        }

        return count;
    }
}