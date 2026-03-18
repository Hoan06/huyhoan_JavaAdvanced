package entity;

public class DigitalProduct extends Product {
    private double capacity;

    public DigitalProduct(String id, String name, double price, double capacity) {
        super(id, name, price);
        this.capacity = capacity;
    }

    public DigitalProduct(double capacity) {
        this.capacity = capacity;
    }

    public DigitalProduct(String id, String name, double price) {
        super(id, name, price);
    }

    public DigitalProduct() {

    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void displayInfo(){
        System.out.printf("| Id : %s | Name : %s | Price : %f | Capacity  %f | \n" , super.getId(), super.getName(), super.getPrice(), this.capacity);
    }
}
