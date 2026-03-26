package hoanhuy.presentation;

import hoanhuy.model.entity.Account;

import java.util.Scanner;

public class ManagerView {
    public static void showManagerMenu(Account account) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.printf("""
                ┌────────────────────────────────────────────────────────────────────┐
                │                    QUẢN LÝ NHÀ HÀNG - MANAGER                      │
                │ Xin chào: %-58s                                                    │
                ├────────────────────────────────┬───────────────────────────────────┤
                │                                │                                   │
                │     1. Quản lý món ăn          │     2. Quản lý bàn ăn             │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     3. Tạo tài khoản Chef      │     4. Danh sách tài khoản        │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     5. Khóa / Mở tài khoản     │     6. Duyệt order                │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     7. Thanh toán hóa đơn      │     8. Thống kê báo cáo           │
                │                                │                                   │
                ├────────────────────────────────┼───────────────────────────────────┤
                │                                │                                   │
                │     9. Xem đánh giá            │     10. Đăng xuất                 │
                │                                │                                   │
                └────────────────────────────────┴───────────────────────────────────┘
                """, account.getFullName());

            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Bạn đã chọn Quản lý món ăn.");
                    break;
                case 2:
                    System.out.println("Bạn đã chọn Quản lý bàn ăn.");
                    break;
                case 3:
                    System.out.println("Bạn đã chọn Tạo tài khoản Chef.");
                    break;
                case 4:
                    System.out.println("Bạn đã chọn Danh sách tài khoản.");
                    break;
                case 5:
                    System.out.println("Bạn đã chọn Khóa / Mở tài khoản.");
                    break;
                case 6:
                    System.out.println("Bạn đã chọn Duyệt order.");
                    break;
                case 7:
                    System.out.println("Bạn đã chọn Thanh toán hóa đơn.");
                    break;
                case 8:
                    System.out.println("Bạn đã chọn Thống kê báo cáo.");
                    break;
                case 9:
                    System.out.println("Bạn đã chọn Xem đánh giá.");
                    break;
                case 10:
                    System.out.println("Đăng xuất thành công.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 10);
    }



}
