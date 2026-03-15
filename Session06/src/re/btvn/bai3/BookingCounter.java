package re.btvn.bai3;

public class BookingCounter implements Runnable {

    String name;
    TicketPool pool;
    int soldCount = 0;

    public BookingCounter(String name, TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            Ticket t = pool.sellTicket(name);

            if (t != null) {
                soldCount++;
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}