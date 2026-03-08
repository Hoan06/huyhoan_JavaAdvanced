package btvn.bai5;

public class Main {

    public static void main(String[] args) {

        User user = new User();

        try {
            user.setAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

    }
}