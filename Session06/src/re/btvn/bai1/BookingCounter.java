package re.btvn.bai1;

public class BookingCounter implements Runnable {

    String name;
    TicketPool roomA;
    TicketPool roomB;
    boolean reverseOrder;

    public BookingCounter(String name, TicketPool roomA, TicketPool roomB, boolean reverseOrder) {
        this.name = name;
        this.roomA = roomA;
        this.roomB = roomB;
        this.reverseOrder = reverseOrder;
    }

    @Override
    public void run() {
        sellCombo();
    }

    public void sellCombo() {

        if (!reverseOrder) {

            synchronized (roomA) {

                Ticket ticketA = roomA.getTicket();
                System.out.println(name + ": Đã lấy vé " + (ticketA != null ? ticketA.id : "NULL"));

                try { Thread.sleep(200); } catch (Exception e) {}

                System.out.println(name + ": Đang chờ vé B...");

                synchronized (roomB) {

                    Ticket ticketB = roomB.getTicket();

                    if (ticketA != null && ticketB != null) {
                        System.out.println(name + " bán combo thành công: "
                                + ticketA.id + " & " + ticketB.id);
                    } else {

                        if (ticketA != null) roomA.returnTicket(ticketA);
                        if (ticketB != null) roomB.returnTicket(ticketB);

                        System.out.println(name + ": Bán combo thất bại");
                    }
                }
            }

        } else {

            synchronized (roomB) {

                Ticket ticketB = roomB.getTicket();
                System.out.println(name + ": Đã lấy vé " + (ticketB != null ? ticketB.id : "NULL"));

                try { Thread.sleep(200); } catch (Exception e) {}

                System.out.println(name + ": Đang chờ vé A...");

                synchronized (roomA) {

                    Ticket ticketA = roomA.getTicket();

                    if (ticketA != null && ticketB != null) {
                        System.out.println(name + " bán combo thành công: "
                                + ticketA.id + " & " + ticketB.id);
                    } else {

                        if (ticketA != null) roomA.returnTicket(ticketA);
                        if (ticketB != null) roomB.returnTicket(ticketB);

                        System.out.println(name + ": Bán combo thất bại");
                    }
                }
            }
        }
    }
}