package hoanhuy.presentation;

import hoanhuy.business.service.IFoodService;
import hoanhuy.business.service.IFoodServiceImpl;
import hoanhuy.model.entity.MenuItem;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class FoodView {
    private static final IFoodService foodService = new IFoodServiceImpl();

    public static void showMenuItemManagement() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                ┌────────────────────────────────────────────────────────────┐
                │                    QUẢN LÝ THỰC ĐƠN                        │
                ├──────────────────────────────┬─────────────────────────────┤
                │     1. Thêm món ăn / đồ uống │     2. Hiển thị ds món ăn   │
                ├──────────────────────────────┼─────────────────────────────┤
                │     3. Cập nhật giá          │     4. Xóa món              │
                ├──────────────────────────────┼─────────────────────────────┤
                │     5. Tìm món ăn theo tên   │     6. Quay lại             │
                └──────────────────────────────┴─────────────────────────────┘
                """);

            choice = Validator.getInt(sc,"Lựa chọn của bạn : ");

            switch (choice) {
                case 1:
                    addFood();
                    break;
                case 2:
                    displayAllFood();
                    break;
                case 3:
                    foodService.updateFood();
                    break;
                case 4:
                    foodService.deleteFood();
                    break;
                case 5:
                    foodService.findByName();
                    break;
                case 6:
                    System.out.println("Quay lại menu quản lý.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 6);
    }

    private static void addFood() {
        foodService.addFood();
    }

    private static void displayAllFood() {
        Scanner sc = new Scanner(System.in);
        int pageSize = 5;
        int currentPage = 1;

        int totalItems = foodService.countFoods();
        if (totalItems == 0) {
            System.out.println("Danh sách món ăn trống!");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        while (true) {
            List<MenuItem> listFoods = foodService.getFoodsByPage(currentPage, pageSize);

            System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│                            DANH SÁCH THỰC ĐƠN                            │");
            System.out.println("├──────┬──────────────────────────┬──────────────┬────────────┬────────────┤");
            System.out.println("│ ID   │ Tên món                  │ Giá          │ Loại       │ Số lượng   │");
            System.out.println("├──────┼──────────────────────────┼──────────────┼────────────┼────────────┤");

            for (MenuItem item : listFoods) {
                System.out.printf("│ %-4d │ %-24s │ %-12s │ %-10s │ %-10d │%n",
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getType(),
                        item.getStock());
            }

            int emptyRows = pageSize - listFoods.size();
            for (int i = 0; i < emptyRows; i++) {
                System.out.printf("│ %-4s │ %-24s │ %-12s │ %-10s │ %-10s │%n", "", "", "", "", "");
            }

            System.out.println("├──────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("│                            Trang %d / %d                                   │%n", currentPage, totalPages, "");
            System.out.println("├──────────────────────────────────────────────────────────────────────────┤");
            System.out.println("│   P. Trang trước        N. Trang sau        0. Thoát                     │");
            System.out.println("└──────────────────────────────────────────────────────────────────────────┘");

            System.out.print("Lựa chọn của bạn: ");
            String choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "P":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("Bạn đang ở trang đầu tiên!");
                    }
                    break;
                case "N":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("Bạn đang ở trang cuối cùng!");
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }



}
