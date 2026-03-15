package re.btvn.bai2;

import java.util.LinkedList;
import java.util.Queue;

class TicketPool {

    String roomName;
    Queue<Ticket> tickets = new LinkedList<>();
    int counter = 1;

    public TicketPool(String roomName, int initial) {
        this.roomName = roomName;

        for (int i = 0; i < initial; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", counter++)));
        }
    }

    public synchronized Ticket sellTicket(String counterName) {

        while (tickets.isEmpty()) {
            try {
                System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Ticket t = tickets.poll();
        System.out.println(counterName + " bán vé " + t.id);

        return t;
    }

    public synchronized void addTickets(int n) {

        for (int i = 0; i < n; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", counter++)));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + n + " vé vào phòng " + roomName);

        notifyAll();
    }
}