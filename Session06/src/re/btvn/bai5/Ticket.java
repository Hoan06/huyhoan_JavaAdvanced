package re.btvn.bai5;

public class Ticket {

    String ticketId;
    String roomName;
    boolean isSold = false;
    boolean isHeld = false;
    long holdExpiryTime;
    boolean isVIP;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }
}
