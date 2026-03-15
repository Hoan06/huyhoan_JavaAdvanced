package re.btvn.bai4;

import java.util.Random;

public class BookingCounter implements Runnable {

    String counterName;
    TicketPool roomA;
    TicketPool roomB;
    int soldCount = 0;

    Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {

        while (true) {

            if (roomA.remainingTickets() == 0 && roomB.remainingTickets() == 0) {
                break;
            }

            Ticket ticket = null;

            if (random.nextBoolean()) {
                ticket = roomA.sellTicket();
            } else {
                ticket = roomB.sellTicket();
            }

            if (ticket == null) {

                if (roomA.remainingTickets() > 0) {
                    ticket = roomA.sellTicket();
                } else if (roomB.remainingTickets() > 0) {
                    ticket = roomB.sellTicket();
                }
            }

            if (ticket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + ticket.ticketId);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}