package re.btvn.bai5;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    String roomName;
    List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int size) {

        this.roomName = roomName;

        for (int i = 1; i <= size; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i), roomName));
        }
    }

    public synchronized Ticket holdTicket(String counterName, boolean vip) {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (!t.isSold && !t.isHeld) {

                t.isHeld = true;
                t.isVIP = vip;
                t.holdExpiryTime = now + 5000;

                System.out.println(counterName +
                        ": Đã giữ vé " + t.ticketId +
                        (vip ? " (VIP)" : "") +
                        ". Vui lòng thanh toán trong 5s");

                return t;
            }
        }

        return null;
    }

    public synchronized void sellHeldTicket(String counterName, Ticket t) {

        if (t != null && t.isHeld && !t.isSold) {

            t.isSold = true;
            t.isHeld = false;

            System.out.println(counterName +
                    ": Thanh toán thành công vé " + t.ticketId);
        }
    }

    public synchronized void releaseExpiredTickets() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld && !t.isSold && t.holdExpiryTime < now) {

                t.isHeld = false;

                System.out.println(
                        "TimeoutManager: Vé " +
                                t.ticketId +
                                " hết hạn giữ, đã trả lại kho");
            }
        }
    }
}
