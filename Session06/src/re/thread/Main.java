package re.thread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountThread countThread = new CountThread();

        Thread countEven = new Thread(new CountRunable());

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 != 0) {
                        System.out.println(i);
                    }
                }
            }
        });

        countThread.start();
//        countThread.join();
        Thread.yield();
        thread3.start();
        thread3.join();
        countEven.start();


//        Thread threadCountMinutes = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 60; i++) {
//                    System.out.println(i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        threadCountMinutes.start();
    }
}
