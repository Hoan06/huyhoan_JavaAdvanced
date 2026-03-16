package bai2;

public class Main {
    public static void main(String[] args) {

        TicketCounter ticket = new TicketCounter();

        Thread t1 = new Thread(new Booking("Quầy 1" , ticket));
        Thread t2 = new Thread(new Booking("Quầy 2" ,  ticket));
        Thread t3 = new Thread(new Booking("Quầy 3" ,  ticket));

        t1.setName("Quầy 1");
        t2.setName("Quầy 2");
        t3.setName("Quầy 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
