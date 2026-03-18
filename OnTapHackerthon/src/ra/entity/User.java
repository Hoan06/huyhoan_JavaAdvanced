package ra.entity;

import ra.business.UserService;

import java.util.Scanner;

public class User {
    private String userId;
    private String userName;
    private int age;
    private String role;
    private double score;

    public User(String userId, String userName, int age, String role, double score) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.role = role;
        this.score = score;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public User inputData() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            User user = new User();
            try {
                System.out.print("Nhập id cho người dùng : ");
                user.setUserId(sc.nextLine());
                System.out.print("Nhập tên người dùng : ");
                user.setUserName(sc.nextLine());
                System.out.print("Nhập tuổi cho người dùng : ");
                user.setAge(Integer.parseInt(sc.nextLine()));
                System.out.print("Nhập vai trò của người dùng ( user / admin ) : ");
                user.setRole(sc.nextLine());
                System.out.print("Nhập điểm của người dùng : ");
                user.setScore(Double.parseDouble(sc.nextLine()));
                if (!checkInput(user)){
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkInput(User user) {
        UserService userService = UserService.getInstance();
        boolean checkId = userService.isCheckUser(user);
        boolean checkNull = false;
        boolean checkRole = false;
        if (user.getUserName().trim().isBlank()){
            checkNull = true;
        }
        if (!user.getRole().equalsIgnoreCase("admin") && !user.getRole().equalsIgnoreCase("user")) {
            checkRole = true;
        }
        if (checkId){
            System.out.println("Id đã tồn tại ! Vui lòng nhập lại .");
        } else {
            System.out.println("Dữ liệu không hợp lệ ! Vui lòng nhập lại .");
        }
        return checkId || checkNull || checkRole;
    }

    public void displayInfo(){
        System.out.printf("| %-10s | %-30s | %-5d | %-10s | %-5.2f | \n" , this.getUserId(), this.getUserName(), this.getAge(), this.getRole(), this.getScore());
    }
}
