package btvn.bai4;

class Order {
    String id;
    double amount;

    public Order(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', amount=" + amount + "}";
    }
}