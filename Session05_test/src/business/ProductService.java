package business;

import exception.InvalidProductException;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    private static final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) throws InvalidProductException {
        boolean idExists = products.stream()
                .anyMatch(p -> p.getId() == product.getId());

        if (idExists) {
            throw new InvalidProductException("Mã sản phẩm " + product.getId() + " đã tồn tại!");
        }

        products.add(product);
        System.out.println("Thêm sản phẩm thành công: " + product.getName());

    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        System.out.println("+----+----------------------+------------+----------+----------------+");
        System.out.printf("| %-2s | %-20s | %-10s | %-8s | %-14s |\n",
                "ID", "Tên sản phẩm", "Giá", "Số lượng", "Danh mục");
        System.out.println("+----+----------------------+------------+----------+----------------+");

        for (Product product : products) {
            System.out.printf("| %-2d | %-20s | %-10.2f | %-8d | %-14s |\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getCategory());
        }

        System.out.println("+----+----------------------+------------+----------+----------------+");
    }

    public void updateProduct(int idInput, int quantityInput) throws InvalidProductException {
        Product productToUpdate = products.stream()
                .filter(p -> p.getId() == idInput)
                .findFirst()
                .orElse(null);

        if (productToUpdate == null) {
            throw new InvalidProductException("Không tìm thấy sản phẩm có ID: " + idInput);
        }

        if (quantityInput < 0) {
            throw new InvalidProductException("Số lượng mới không được âm!");
        }

        productToUpdate.setQuantity(quantityInput);
        System.out.println("Đã cập nhật số lượng thành công cho sản phẩm ID " + idInput);
    }

    public void deleteProduct() {
        boolean removed = products.removeIf(product -> product.getQuantity() == 0);

        if (removed) {
            System.out.println("Đã xóa thành công các sản phẩm hết hàng!");
        } else {
            System.out.println("Không có sản phẩm nào hết hàng để xóa!");
        }
    }
}
