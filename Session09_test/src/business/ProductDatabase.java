package business;

import entity.DigitalProduct;
import entity.PhysicalProduct;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static final List<Product> products = new ArrayList<>();

    public void createProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (product.getId().equals(products.get(i).getId())) {
                products.set(i, product);
                System.out.println("Cập nhật sản phẩm thành công !");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm muốn cập nhật !");
    }

    public void updateProduct(String id, String newName, double newPrice) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setName(newName);
                p.setPrice(newPrice);
                System.out.println("Cập nhật sản phẩm thành công !");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm có ID: " + id);
    }

    public void deleteProduct(String idDelete) {
        boolean removed = products.removeIf(p -> p.getId().equals(idDelete));
        if (removed) {
            System.out.println("Xóa sản phẩm thành công !");
        } else {
            System.out.println("Không tìm thấy sản phẩm muốn xóa !");
        }
    }

    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return;
        }
        for (Product product : products) {
            if (product instanceof PhysicalProduct) {
                ((PhysicalProduct) product).displayInfo();
            } else if (product instanceof DigitalProduct) {
                ((DigitalProduct) product).displayInfo();
            }
        }
    }
}