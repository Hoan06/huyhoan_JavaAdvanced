package re.btvn.btth;

import java.util.ArrayList;

public class RepositoryTicket {
    public static int totalTickets = 10;

    public synchronized static void sellTicket() {
        totalTickets--;
        System.out.println("Số vé còn lại : " +  totalTickets);
    }
}
