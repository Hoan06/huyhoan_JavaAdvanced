package btvn.bai5;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService doctorService = new DoctorService();

        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ BÁC SĨ TRỰC CA =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ mới");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    doctorService.displayAllDoctors();
                    break;

                case "2":
                    System.out.print("Nhập mã bác sĩ: ");
                    String doctorId = sc.nextLine();

                    System.out.print("Nhập họ tên bác sĩ: ");
                    String fullName = sc.nextLine();

                    System.out.print("Nhập chuyên khoa: ");
                    String specialty = sc.nextLine();

                    doctorService.addDoctor(doctorId, fullName, specialty);
                    break;

                case "3":
                    doctorService.statisticBySpecialty();
                    break;

                case "4":
                    System.out.println("Đã thoát chương trình !");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 4 !");
            }
        }
    }
}
