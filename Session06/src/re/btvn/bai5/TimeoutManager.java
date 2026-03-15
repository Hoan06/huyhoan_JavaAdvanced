package re.btvn.bai5;

public class TimeoutManager implements Runnable {

    TicketPool roomA;
    TicketPool roomB;
    TicketPool roomC;

    public TimeoutManager(TicketPool roomA,
                          TicketPool roomB,
                          TicketPool roomC) {

        this.roomA = roomA;
        this.roomB = roomB;
        this.roomC = roomC;
    }

    @Override
    public void run() {

        while (true) {

            roomA.releaseExpiredTickets();
            roomB.releaseExpiredTickets();
            roomC.releaseExpiredTickets();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}