package hoanhuy.presentation;

import hoanhuy.business.service.IAccountLoginAndRegister;
import hoanhuy.business.service.IAccountLoginAndRegisterImpl;
import hoanhuy.business.service.IReviewService;
import hoanhuy.business.service.IReviewServiceImpl;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Review;
import hoanhuy.utils.Color;

import java.util.List;
import java.util.Scanner;

public class RestaurantView {
    private static final IAccountLoginAndRegister accountLoginAndRegister = new IAccountLoginAndRegisterImpl();
    private static final IReviewService reviewService = new IReviewServiceImpl();

    public static void showMenuRestaurant(){
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("""
                ┌───────────────────────┬
                │       WELCOME TO      │
                │ Restaurant Management │
                ┌───────────────────────┬─────────────────────────────┐
                │                       │                             │
                │     1. Đăng nhập      │     2. Đăng ký khách hàng   │
                │                       │                             │
                ├───────────────────────┼─────────────────────────────┤
                │                       │                             │
                │     3. Xem đánh giá   │     4. Thoát                │
                │                       │                             │
                └───────────────────────┴─────────────────────────────┘
                """);
            System.out.print("Lựa chọn của bạn : ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    displayAllReviews();
                    break;
                case 4:
                    System.out.println("Chương trình kết thúc !");
                    break;
                default:
                    System.out.println(Color.YELLOW + "Lựa chọn không hợp lệ !" + Color.RESET);
            }
        } while (choice != 4);
    }

    private static void register(){
        accountLoginAndRegister.register();
    }

    private static void login() {
        Account currentAccount = accountLoginAndRegister.login();

        if (currentAccount != null) {
            switch (currentAccount.getRole()) {
                case MANAGER:
                    ManagerView.showManagerMenu(currentAccount);
                    break;
                case CHEF:
                    break;
                case CUSTOMER:
                    CustomerView.showCustomerManagement(currentAccount);
                    break;
            }
        } else {
            System.out.println("Đăng nhập thất bại .");
        }
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
