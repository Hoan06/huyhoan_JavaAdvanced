package factory;

import entity.DigitalProduct;
import entity.PhysicalProduct;
import entity.Product;

public class ProductFactory {
    private static ProductFactory instance = new  ProductFactory();

    public static ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }

    public static Product createProduct(String type) {
        if (type.equalsIgnoreCase("physical")) {
            return new PhysicalProduct();
        } else if (type.equalsIgnoreCase("digital")) {
            return new DigitalProduct();
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

}
