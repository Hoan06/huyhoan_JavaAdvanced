package bai3;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Thread t1 = new Thread(new Producer(warehouse));
        Thread t2 = new Thread(new Consumer(warehouse));

        t1.start();
        t2.start();
    }
}
