package re.btvn.bai1;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    String roomName;
    Queue<Ticket> tickets = new LinkedList<Ticket>();

    public TicketPool(String roomName , int total) {
        this.roomName = roomName;

        for (int i = 1 ; i <= total ; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
        }
    }

    public Ticket getTicket() {
        if (tickets.isEmpty()) {
            return null;
        }
        return tickets.poll();
    }

    public void returnTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
