package hoanhuy.presentation;

import hoanhuy.business.service.IOrderItemServiceImpl;
import hoanhuy.business.service.IOrderItemsService;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.OrderItem;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class ChefView {
    private static final IOrderItemsService orderItemsService = new IOrderItemServiceImpl();

    public static void showChefManagement(Account account) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.printf("""
                ┌────────────────────────────────────────────────────────────────────┐
                │ Xin chào: %-20s                                                    │
                ├────────────────────────────────┬───────────────────────────────────┤
                │                                │                                   │
                │     1. Hiển thị danh sách món  │     2. Cập nhật trạng thái món    │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     3. Đăng xuất               │                                   │
                │                                │                                   │
                └────────────────────────────────┴───────────────────────────────────┘
                """, account.getFullName());

            choice = Validator.getInt(sc , "Lựa chọn của bạn : ");

            switch (choice) {
                case 1:
                    getAllOrder();
                    break;
                case 2:
                    orderItemsService.updateStatusByChef();
                    break;
                case 3:
                    System.out.println("Đăng xuất .");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 3);
    }

    private static void getAllOrder() {
        List<OrderItem> orderItems = orderItemsService.findAllOfChef();
        if (orderItems.isEmpty()) {
            System.out.println("Chưa có đơn nào !");
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
