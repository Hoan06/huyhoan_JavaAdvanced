package hoanhuy.presentation;

import hoanhuy.business.service.IAccountLoginAndRegister;
import hoanhuy.business.service.IAccountLoginAndRegisterImpl;
import hoanhuy.model.entity.Account;
import hoanhuy.utils.Color;

import java.util.Scanner;

public class RestaurantView {
    private static final IAccountLoginAndRegister accountLoginAndRegister = new IAccountLoginAndRegisterImpl();

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
                    break;
            }
        } else {
            System.out.println("Đăng nhập thất bại .");
        }
    }

}
