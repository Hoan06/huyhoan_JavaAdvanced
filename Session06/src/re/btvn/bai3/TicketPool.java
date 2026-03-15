package re.btvn.bai3;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    String roomName;
    Queue<Ticket> tickets = new LinkedList<>();
    int counter = 1;

    public TicketPool(String roomName , int initial) {
        this.roomName = roomName;

        for (int i = 0 ; i < initial; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", counter++)));
        }
    }

    public synchronized Ticket sellTicket(String counterName){
        if (tickets.isEmpty()){
            System.out.println(counterName + ": Hết vé phòng " + roomName);
            return null;
        }
        Ticket t = tickets.poll();
        System.out.println(counterName + " đã bán vé " + t.id);
        return t;
    }

    public synchronized void addTickets(int count) {

        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", counter++)));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public int remainingTickets() {
        return tickets.size();
    }

}
