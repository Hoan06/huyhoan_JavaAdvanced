package structuralPatterns.facade;

public class Facade {
    public static void main(String[] args) {
        FacadeRestaurant facadeRestaurant = new FacadeRestaurant();

        facadeRestaurant.order();
        facadeRestaurant.pay();
    }
}
