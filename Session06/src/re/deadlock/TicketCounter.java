package re.deadlock;

public class TicketCounter {
    private static int totalTicket = 10;

    public synchronized void sellTicket(Thread t){
        System.out.println("Thread " + t.getName() + " đã mua được vé.");
        totalTicket--;
    }
}
