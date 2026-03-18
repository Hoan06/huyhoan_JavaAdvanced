package ra.presentation;

import ra.business.UserService;
import ra.entity.User;

import java.util.Scanner;

public class UserView {
    private static  UserService userService = UserService.getInstance();

    public static void showMenuUser() {
        Scanner sc = new Scanner(System.in);
        int choice;


        do {
            System.out.println("""
                    ********************* QUẢN LÝ NGƯỜI DÙNG *********************
                    
                    1. Hiển thị danh sách toàn bộ người dùng
                    2. Thêm mới người dùng
                    3. Cập nhật thông tin người dùng theo mã
                    4. Xóa người dùng theo mã
                    5. Tìm kiếm người dùng theo tên
                    6. Lọc danh sách người dùng ADMIN
                    7. Sắp xếp danh sách theo điểm đánh giá giảm dần
                    8. Thoát                    
                    """);
            System.out.print("Lựa chọn của bạn : ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    displayUser();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    searchUserByName();
                    break;
                case 6:
                    filterAdmin();
                    break;
                case 7:
                    sortScoreUser();
                    break;
                case 8:
                    System.out.println("Chương trình kết thúc !");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ !");
            }
        } while (choice != 8);
    }

    private static void displayUser(){
        userService.displayAllUsers();
    }

    private static void addUser(){
        Scanner sc = new Scanner(System.in);
        userService.addUser();
    }

    private static void updateUser(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id người dùng muốn cập nhập : ");
        String idUpdate = sc.nextLine();
        userService.updateUser(idUpdate);
    }

    private static void deleteUser(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id người dùng muốn xóa : ");
        String idDel = sc.nextLine();
        userService.deleteUser(idDel);
    }

    private static void searchUserByName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên người dùng muốn tìm kiếm : ");
        String nameSearch = sc.nextLine();
        userService.findUser(nameSearch);
    }

    private static void filterAdmin(){
        userService.filterAdmin();
    }

    private static void sortScoreUser(){
        userService.sortUser();
    }
}
