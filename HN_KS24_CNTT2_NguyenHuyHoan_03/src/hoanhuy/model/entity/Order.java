package hoanhuy.model.entity;
import java.sql.Timestamp;

public class Order {
    private int id;
    private int customerId;
    private int tableId;
    private boolean isPay;
    private Timestamp createdAt;

    public Order() {
    }

    public Order(int id, int customerId, int tableId, boolean isPay, Timestamp createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.tableId = tableId;
        this.isPay = isPay;
        this.createdAt = createdAt;
    }

    public Order(int customerId, int tableId, boolean isPay) {
        this.customerId = customerId;
        this.tableId = tableId;
        this.isPay = isPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

