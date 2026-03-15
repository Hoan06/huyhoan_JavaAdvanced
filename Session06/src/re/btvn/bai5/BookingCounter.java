package re.btvn.bai5;

import java.util.Random;

public class BookingCounter implements Runnable {

    String counterName;
    TicketPool roomA;
    TicketPool roomB;
    TicketPool roomC;

    Random random = new Random();

    public BookingCounter(String counterName,
                          TicketPool roomA,
                          TicketPool roomB,
                          TicketPool roomC) {

        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.roomC = roomC;
    }

    @Override
    public void run() {

        while (true) {

            TicketPool pool;

            int r = random.nextInt(3);

            if (r == 0) pool = roomA;
            else if (r == 1) pool = roomB;
            else pool = roomC;

            boolean vip = random.nextBoolean();

            Ticket t = pool.holdTicket(counterName, vip);

            if (t != null) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}

                pool.sellHeldTicket(counterName, t);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}
