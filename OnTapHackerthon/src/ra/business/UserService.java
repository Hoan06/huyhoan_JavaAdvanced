package ra.business;

import ra.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserService {
    private static UserService instance = new UserService();
    private static final List<User> users = new ArrayList();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean isCheckUser(User user) {
        boolean checkId = users.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()));
        return checkId;
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Danh sách trống !");
            return;
        }
        System.out.printf("| %-10s | %-30s | %-5s | %-10s | %-5.2s | \n" , "ID", "Name", "Age", "Role" , "Score" );
        for (User user : users) {
            user.displayInfo();
        }
    }

    public void addUser() {
        User user = new User();
        user = user.inputData();
        users.add(user);
        System.out.println("Thêm người dùng thành công .");
    }

    public void updateUser(String idUpdate){
        Optional<User> optionalUser = findById(idUpdate);

        if (optionalUser.isEmpty()) {
            System.out.println("Mã người dùng không tồn tại trong hệ thống");
            return;
        }

        User user = optionalUser.get();
        Scanner sc = new Scanner(System.in);

        System.out.print("""
            Bạn muốn cập nhật : 
            1. Điểm 
            2. Tên 
            3. Tuổi
            4. Vai trò
            """);

        System.out.print("Lựa chọn của bạn : ");
        int choose = Integer.parseInt(sc.nextLine());

        switch (choose) {
            case 1:
                System.out.print("Nhập điểm mới : ");
                user.setScore(Double.parseDouble(sc.nextLine()));
                break;
            case 2:
                System.out.print("Nhập tên mới : ");
                user.setUserName(sc.nextLine());
                break;
            case 3:
                System.out.print("Nhập tuổi mới : ");
                user.setAge(Integer.parseInt(sc.nextLine()));
                break;
            case 4:
                System.out.print("Nhập vai trò mới : ");
                String role = sc.nextLine();
                if (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("user")) {
                    System.out.println("Vai trò không hợp lệ !");
                    return;
                }
                user.setRole(role);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ !");
                return;
        }

        System.out.println("Cập nhật thông tin người dùng thành công !");
    }

    public Optional<User> findById(String id) {
        return users.stream()
                .filter(u -> u.getUserId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void findUser(String nameFind){
        List<User> userFinds = new ArrayList<>();
        userFinds = users.stream().filter(u -> u.getUserName().toLowerCase().contains(nameFind.toLowerCase())).toList();
        if (userFinds.isEmpty()) {
            System.out.println("Không tìm thấy user nào !");
            return;
        }
        System.out.printf("| %-10s | %-30s | %-5s | %-10s | %-5.2s | \n" , "ID", "Name", "Age", "Role" , "Score" );
        for (User user : userFinds) {
            user.displayInfo();
        }
    }

    public void deleteUser(String idDelete){
        boolean removeUser = users.removeIf(u -> u.getUserId().equals(idDelete));
        if (removeUser) {
            System.out.println("Xóa người dùng thành công .");
        } else {
            System.out.println("Mã người dùng không tồn tại !");
        }
    }

    public void filterAdmin(){
        List<User> userFinds = new ArrayList<>();
        userFinds = users.stream().filter(u -> u.getRole().equalsIgnoreCase("admin")).toList();
        if (userFinds.isEmpty()) {
            System.out.println("Không tìm thấy user nào !");
            return;
        }
        System.out.printf("| %-10s | %-30s | %-5s | %-10s | %-5.2s | \n" , "ID", "Name", "Age", "Role" , "Score" );
        for (User user : userFinds) {
            user.displayInfo();
        }
    }

    public void sortUser(){
        UserSorted sorted = new UserSorted();
        sorted.setSortStrategy(new SortByScore());
        sorted.sort(users);
        System.out.println("Sắp xếp thành công .");
    }






}
