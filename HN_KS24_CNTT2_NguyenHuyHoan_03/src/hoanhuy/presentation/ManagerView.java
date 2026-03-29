package hoanhuy.presentation;

import hoanhuy.business.service.IReviewService;
import hoanhuy.business.service.IReviewServiceImpl;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Review;

import java.util.List;
import java.util.Scanner;

public class ManagerView {
    private static final IReviewService reviewService = new IReviewServiceImpl();

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
                    FoodView.showMenuItemManagement();
                    break;
                case 2:
                    TableView.showTableManagement();
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
                    displayAllReviews();
                    break;
                case 10:
                    System.out.println("Đăng xuất thành công.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 10);
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


}
