package entity;

public class PhysicalProduct extends Product{
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public PhysicalProduct(double weight) {
        this.weight = weight;
    }

    public PhysicalProduct(String id, String name, double price) {
        super(id, name, price);
    }

    public PhysicalProduct() {

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void displayInfo(){
        System.out.printf("| Id : %s | Name : %s | Price : %f | Weight  %f | \n" , super.getId(), super.getName(), super.getPrice(), this.weight);
    }
}
