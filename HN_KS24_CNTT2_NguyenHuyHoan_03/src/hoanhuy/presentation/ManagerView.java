package hoanhuy.presentation;

import com.mysql.cj.xdevapi.Schema;
import hoanhuy.business.service.*;
import hoanhuy.model.entity.*;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class ManagerView {
    private static final IReviewService reviewService = new IReviewServiceImpl();
    private static final IAccountLoginAndRegister accountLoginAndRegister = new IAccountLoginAndRegisterImpl();
    private static final IManagerService managerService = new IManagerServiceImpl();

    public static void showManagerMenu(Account account) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.printf("""
                ┌────────────────────────────────────────────────────────────────────┐
                │                    QUẢN LÝ NHÀ HÀNG - MANAGER                      │
                    Xin chào: %-58s
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

            choice = Validator.getInt(sc,"Lựa chọn của bạn : ");

            switch (choice) {
                case 1:
                    FoodView.showMenuItemManagement();
                    break;
                case 2:
                    TableView.showTableManagement();
                    break;
                case 3:
                    accountLoginAndRegister.addAccountChef();
                    break;
                case 4:
                    displayAllAccounts();
                    break;
                case 5:
                    managerService.banAccount();
                    break;
                case 6:
                    managerService.browseOrderItems();
                    break;
                case 7:
                    managerService.payOrder();
                    break;
                case 8:
                    managerService.statisticRevenue();
                    break;
                case 9:
                    displayAllReviews();
                    break;
                case 10:
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
        } while (choice != 10);
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


    private static void displayAllReviews() {
        Scanner sc = new Scanner(System.in);
        int pageSize = 5;
        int currentPage = 1;

        int totalReviews = reviewService.countReviews();
        if (totalReviews == 0) {
            System.out.println("Danh sách đánh giá trống!");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalReviews / pageSize);

        while (true) {
            List<Review> reviews = reviewService.getReviewsByPage(currentPage, pageSize);

            System.out.println("┌──────┬────────────┬──────┬──────────────────────────────────────┐");
            System.out.println("│ ID   │ CustomerId │ Sao  │ Bình luận                            │");
            System.out.println("├──────┼────────────┼──────┼──────────────────────────────────────┤");

            for (Review item : reviews) {
                System.out.printf("│ %-4d │ %-10d │ %-4d │ %-36s │%n",
                        item.getId(),
                        item.getCustomerId(),
                        item.getStars(),
                        item.getComment());
            }

            int emptyRows = pageSize - reviews.size();
            for (int i = 0; i < emptyRows; i++) {
                System.out.printf("│ %-4s │ %-10s │ %-4s │ %-36s │%n", "", "", "", "");
            }

            System.out.println("├─────────────────────────────────────────────────────────────────┤");
            System.out.printf("│ Trang %d / %d                                                     │%n", currentPage, totalPages);
            System.out.println("├─────────────────────────────────────────────────────────────────┤");
            System.out.println("│ P. Trang trước    N. Trang sau    0. Thoát                      │");
            System.out.println("└─────────────────────────────────────────────────────────────────┘");

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

    private static void displayAllAccounts() {
        Scanner sc = new Scanner(System.in);
        int pageSize = 5;
        int currentPage = 1;

        int totalItems = accountLoginAndRegister.countAccounts();
        if (totalItems == 0) {
            System.out.println("Danh sách món ăn trống!");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        while (true) {
            List<Account> listAccounts = accountLoginAndRegister.findByPage(currentPage, pageSize);

            System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│                            DANH SÁCH TÀI KHOẢN                           │");
            System.out.println("├──────┬──────────────────────────┬──────────────┬────────────┬────────────┤");
            System.out.println("│ ID   │ Họ và tên                │ Tài khoản    │ Vai trò    │ Trạng thái │");
            System.out.println("├──────┼──────────────────────────┼──────────────┼────────────┼────────────┤");

            for (Account item : listAccounts) {
                System.out.printf("│ %-4d │ %-24s │ %-12s │ %-10s │ %-10s │%n",
                        item.getAccountId(),
                        item.getFullName(),
                        item.getUsername(),
                        item.getRole(),
                        item.isBan() ? "Khóa" : "Mở");
            }

            int emptyRows = pageSize - listAccounts.size();
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
