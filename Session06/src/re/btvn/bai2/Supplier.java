package re.btvn.bai2;

class Supplier implements Runnable {

    TicketPool pool;

    public Supplier(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}

        pool.addTickets(3);
    }
}