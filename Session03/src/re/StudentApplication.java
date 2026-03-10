package re;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApplication {
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("""
                    ==================MENU==================
                    1. Hiển thị danh sách
                    2. Thêm mới
                    3. Cập nhật 
                    4. Xóa
                    5. Sắp xếp
                    6. Tìm kiếm
                    0. Thoát
                    """);
            System.out.print("Lựa chọn của bạn : ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                default -> System.out.println("Lựa chọn không hợp lệ !!!");
            }

        } while (choice != 0);
    }

    private static void addStudent() {
        students.add(Student.inputData());
    }

    private static void displayStudents(){

    }
}
