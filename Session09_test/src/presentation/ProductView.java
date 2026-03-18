package presentation;

import business.ProductDatabase;
import entity.DigitalProduct;
import entity.PhysicalProduct;
import entity.Product;
import factory.ProductFactory;

import java.util.Scanner;

public class ProductView {
    private static final ProductDatabase productDatabase = new ProductDatabase();
    private static final Scanner sc = new Scanner(System.in);

    public static void showMenuProduct() {
        int choice;
        do {
            System.out.println("""
                \n---------------------- QUẢN LÝ SẢN PHẨM----------------------
                1. Thêm mới sản phẩm.
                2. Xem danh sách sản phẩm.
                3. Cập nhật thông tin sản phẩm.
                4. Xoá sản phẩm.
                5. Thoát.
                ---------------------------------------------------------------------
                """);
            System.out.print("Lựa chọn của bạn: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    String idDel = sc.nextLine();
                    productDatabase.deleteProduct(idDel);
                    break;
                case 5:
                    System.out.println("Chương trình kết thúc!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 5);
    }

    public static void addProduct() {
        System.out.print("Nhập loại sản phẩm muốn thêm (physical/digital): ");
        String type = sc.nextLine();

        try {
            Product product = ProductFactory.createProduct(type);
            if (product == null) {
                System.out.println("Loại sản phẩm không hợp lệ!");
                return;
            }

            System.out.print("Nhập id sản phẩm: ");
            product.setId(sc.nextLine());
            System.out.print("Nhập tên sản phẩm: ");
            product.setName(sc.nextLine());
            System.out.print("Nhập giá sản phẩm: ");
            product.setPrice(Double.parseDouble(sc.nextLine()));

            if (product instanceof PhysicalProduct) {
                System.out.print("Nhập trọng lượng: ");
                ((PhysicalProduct) product).setWeight(Double.parseDouble(sc.nextLine()));
            } else if (product instanceof DigitalProduct) {
                System.out.print("Nhập dung lượng: ");
                ((DigitalProduct) product).setCapacity(Double.parseDouble(sc.nextLine()));
            }

            productDatabase.createProduct(product);
            System.out.println("Thêm sản phẩm thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập đúng định dạng số cho giá/trọng lượng/dung lượng!");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    public static void displayProduct() {
        productDatabase.displayAllProducts();
    }

    public static void updateProduct() {
        try {
            System.out.print("Nhập ID cần cập nhật: ");
            String uId = sc.nextLine();
            System.out.print("Tên mới: ");
            String uName = sc.nextLine();
            System.out.print("Giá mới: ");
            double uPrice = Double.parseDouble(sc.nextLine());

            productDatabase.updateProduct(uId, uName, uPrice);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Giá phải là một con số!");
        }
    }
}