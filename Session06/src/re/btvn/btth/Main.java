package re.btvn.btth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Thread shop1 = new Thread(new Runnable() {

            @Override
            public void run() {
                int counter = 0;
                while (RepositoryTicket.totalTickets > 0) {
                    RepositoryTicket.sellTicket();
                    System.out.println("Shop 1 đã mua được 1 vé .");
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Số vé shop 1 này mua được là : " + counter);
            }
        });

        Thread shop2 = new Thread(new Runnable() {

            @Override
            public void run() {
                int counter = 0;
                while (RepositoryTicket.totalTickets > 0) {
                    RepositoryTicket.sellTicket();
                    System.out.println("Shop 2 đã mua được 1 vé .");
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Số vé shop 2 này mua được là : " + counter);
            }
        });

        Thread shop3 = new Thread(new Runnable() {

            @Override
            public void run() {
                int counter = 0;
                while (RepositoryTicket.totalTickets > 0) {
                    RepositoryTicket.sellTicket();
                    System.out.println("Shop 3 đã mua được 1 vé .");
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Số vé shop 3 này mua được là : " + counter);
            }
        });
        shop1.start();
        shop2.start();
        shop3.start();
    }
}
