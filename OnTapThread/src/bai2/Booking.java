package bai2;

public class Booking implements Runnable {
    String name;
    TicketCounter ticket;

    public Booking(String name, TicketCounter ticket) {
        this.name = name;
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (ticket.tickets > 0) {
            ticket.sellTicket();
        }
    }
}
