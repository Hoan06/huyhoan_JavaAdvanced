package presentation;

import business.ProductService;
import exception.InvalidProductException;
import model.Product;

import java.util.Scanner;

public class ProductView {
    private static final ProductService productService = new ProductService();

    public static void showProductView() throws InvalidProductException {

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("""
                    +----------PRODUCTMANAGEMENTSYSTEM----------+
                    |1. Thêm sản phẩm mới .                     |
                    |2. Hiển thị danh sách sản phẩm .           |
                    |3. Cập nhật số lượng sản phẩm theo id .    |
                    |4. Xóa sản phẩm đã hết hàng .              |
                    |5. Thoát .                                 |
                    +-------------------------------------------+
                """);
            System.out.print("Lựa chọn của bạn : ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    addProductNew();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    updateProductView();
                    break;
                case 4:
                    deleteProductQuantity();
                    break;
                case 5:
                    System.out.println("Chương trình kết thúc !");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ !");
            }
        } while(choice != 5);
    }

    private static void addProductNew() {
        Scanner sc = new Scanner(System.in);
        Product product = new Product();

        try {
            System.out.print("Nhập ID sản phẩm: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            product.setId(id);

            System.out.print("Nhập tên sản phẩm: ");
            product.setName(sc.nextLine().trim());

            System.out.print("Nhập giá sản phẩm: ");
            double price = Double.parseDouble(sc.nextLine().trim());
            if (price < 0) throw new InvalidProductException("Giá không được âm!");
            product.setPrice(price);

            System.out.print("Nhập số lượng tồn kho: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());
            if (quantity < 0) throw new InvalidProductException("Số lượng không được âm!");
            product.setQuantity(quantity);

            System.out.print("Nhập danh mục sản phẩm: ");
            product.setCategory(sc.nextLine().trim());

            productService.addProduct(product);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số hợp lệ cho ID, giá hoặc số lượng!");
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi không xác định: " + e.getMessage());
        }
    }

    private static void displayAllProducts() {
        productService.displayProducts();
    }

    private static void updateProductView() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập ID sản phẩm muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Nhập số lượng mới: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());

            productService.updateProduct(id, quantity);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số hợp lệ!");
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi không xác định: " + e.getMessage());
        }
    }

    private static void deleteProductQuantity(){
        productService.deleteProduct();
    }
}
