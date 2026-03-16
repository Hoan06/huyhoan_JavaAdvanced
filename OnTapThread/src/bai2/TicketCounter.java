package bai2;

public class TicketCounter {
    int tickets = 10;

    synchronized void sellTicket(){
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + " bán vé số " + tickets);
            tickets--;
        } else {
            System.out.println("Hết vé !");
        }

    }
}
