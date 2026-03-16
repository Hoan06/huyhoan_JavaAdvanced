package re.projects.utils;

public class Printer {
    // Các phương thức chung cho các lớp sử dụng

    // in thông tin bthg
    public static void printInfo(String content){
        System.out.println(content);
    }

    // Cảnh báo : Vàng
    public static void printWarn(String content){
        System.out.println(content);
    }

    // In lỗi : Đỏ
    public static void printError(String content){
        System.out.println(content);
    }
}
