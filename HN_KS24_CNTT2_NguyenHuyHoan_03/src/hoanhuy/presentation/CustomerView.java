package hoanhuy.presentation;

import hoanhuy.business.dao.IOrderDao;
import hoanhuy.business.dao.IOrderDaoImpl;
import hoanhuy.business.service.*;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.MenuItem;
import hoanhuy.model.entity.Order;
import hoanhuy.model.entity.OrderItem;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private static final IFoodService foodService = new IFoodServiceImpl();
    private static final IOrderService orderService = new IOrderServiceImpl();
    private static final IOrderItemsService orderItemsService = new IOrderItemServiceImpl();
    private static final ITableService tableService = new ITableServiceImpl();
    private static final IReviewService reviewService = new IReviewServiceImpl();

    public static void showCustomerManagement(Account account) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.printf("""
                ┌────────────────────────────────────────────────────────────────────┐
                    Xin chào: %s                                                       
                ├────────────────────────────────┬───────────────────────────────────┤
                │                                │                                   │
                │     1. Danh sách món ăn        │     2. Chọn bàn                   │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     3. Gọi món                 │     4. Theo dõi món ăn đã đặt     │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     5. Hủy món                 │     6. Đánh giá nhà hàng          │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     7. Đăng xuất               │                                   │
                │                                │                                   │
                └────────────────────────────────┴───────────────────────────────────┘
                """, account.getFullName());

            choice = Validator.getInt(sc , "Lựa chọn của bạn : ");

            switch (choice) {
                case 1:
                    displayAllFood();
                    break;
                case 2:
                    orderService.orderTable(account.getAccountId());
                    break;
                case 3:
                    addOrderItem(account);
                    break;
                case 4:
                    getOrderItems(account);
                    break;
                case 5:
                    orderItemsService.cancelOrderItems();
                    break;
                case 6:
                    reviewService.insertReview(account.getAccountId());
                    break;
                case 7:
                    if (confirmLogout(sc)) {
                        System.out.println("Đăng xuất thành công.");
                    } else {
                        choice = 0;
                        System.out.println("Đã hủy đăng xuất.");
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 7);
    }

    private static boolean confirmLogout(Scanner sc) {
        System.out.println("""
            Bạn có chắc chắn muốn đăng xuất?
            1. Có
            2. Không
            """);
        int choice = Validator.getInt(sc, "Lựa chọn của bạn: ");
        return choice == 1;
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


    private static void addOrderItem(Account account) {
        Scanner sc = new Scanner(System.in);
        int orderId = orderItemsService.findOrderId(account);
        if (orderId == -1){
            System.out.println("Bạn chưa đặt bàn , hãy đặt bàn trước !");
            return;
        }
        List<MenuItem> menuItems = foodService.getAllFood();
        System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                            DANH SÁCH THỰC ĐƠN                            │");
        System.out.println("├──────┬──────────────────────────┬──────────────┬────────────┬────────────┤");
        System.out.println("│ ID   │ Tên món                  │ Giá          │ Loại       │ Số lượng   │");
        System.out.println("├──────┼──────────────────────────┼──────────────┼────────────┼────────────┤");

        for (MenuItem item : menuItems) {
            System.out.printf("│ %-4d │ %-24s │ %-12s │ %-10s │ %-10d │%n",
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getType(),
                    item.getStock());
        }

        System.out.println("└──────────────────────────────────────────────────────────────────────────┘");
        System.out.print("Nhập mã món ăn muốn gọi : ");
        int foodId = Integer.parseInt(sc.nextLine());
        if (Validator.isValidInt(foodId)) {
            System.out.println(Color.YELLOW + "Id món ăn không hợp lệ !" + Color.RESET);
            return;
        }
        if (foodService.findFoodById(foodId) == null) {
            System.out.println(Color.YELLOW + "Món ăn không tồn tại !" + Color.RESET);
            return;
        }
        System.out.print("Nhập số lượng bạn muốn đặt : ");
        int quantity =  Integer.parseInt(sc.nextLine());
        if (Validator.isValidInt(quantity)) {
            System.out.println(Color.YELLOW + "Số lượng không hợp lệ !" + Color.RESET);
            return;
        }
        orderItemsService.orderFood(orderId, foodId, quantity);
    }

    private static void getOrderItems(Account account) {
        int orderId = orderItemsService.findOrderId(account);
        if (orderId == -1) {
            System.out.println("Bạn chưa đặt bàn nên chưa có món nào để theo dõi !");
            return;
        }

        List<OrderItem> orderItems = orderItemsService.getAllOrderItems(orderId);
        if (orderItems == null || orderItems.isEmpty()) {
            System.out.println("Bạn chưa đặt món nào !");
            return;
        }
        System.out.println("+------+----------+-------------+----------+--------------------+");
        System.out.printf("| %-4s | %-8s | %-11s | %-8s | %-18s |%n",
                "ID", "Order ID", "Id món", "Số lượng", "Trạng thái");
        System.out.println("+------+----------+-------------+----------+--------------------+");

        for (OrderItem orderItem : orderItems) {
            System.out.printf("| %-4d | %-8d | %-11d | %-8d | %-18s |%n",
                    orderItem.getId(),
                    orderItem.getOrderId(),
                    orderItem.getMenuItemId(),
                    orderItem.getQuantity(),
                    orderItem.getStatus());
        }

        System.out.println("+------+----------+-------------+----------+--------------------+");
    }
}
