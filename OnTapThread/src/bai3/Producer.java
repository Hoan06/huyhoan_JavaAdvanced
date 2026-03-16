package bai3;

public class Producer implements Runnable {
    Warehouse warehouse;

    public Producer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    @Override
    public void run() {
        try {
            while (true) {
                warehouse.produce(1);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
