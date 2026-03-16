package bai3;

public class Consumer implements Runnable {
    Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    @Override
    public void run() {
        while (true) {
            try {
                warehouse.consume();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
