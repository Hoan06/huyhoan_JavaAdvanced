package exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyCheckedException myCheckedException = new MyCheckedException("Không có người yêu");
        MyUncheckedException myUncheckedException = new MyUncheckedException("Thức khuya , dậy sớm");



        Exception e = new Exception();

//        try {
//            int in = inputInteger();
//            System.out.println(in);
//        } catch (MyCheckedException e) {
//
//        }



    }

    public static int inputInteger() throws MyUncheckedException {
        Scanner sc = new Scanner(System.in);

        // hãy nhập đến khi nào thỏa mãn là 1 số nguyên dương thì trả về
        while (true){
            try {
                // khối code sinh ra ngoại lệ
                System.out.print("Nhập 1 số nguyên : ");
                int a = Integer.parseInt(sc.nextLine());

                if (a <= 0){
                    System.out.println("Không được nhập số âm hoặc 0 !");
                    continue;
                }
                return a;
            } catch (NumberFormatException ex){ // tên ngoại lệ muốn bắt
                // logic xử lí ngoại lệ
                System.out.println("Bạn phải nhập 1 số nguyên !!!");
                // in nội dung lỗi
//                System.out.println(ex.getMessage());
//                throw new MyUncheckedException("Lỗi mới");
            } finally {
                // luôn chạy sau khi xử lý : dọn dẹp , đóng các kết nối
                System.out.println("Kết thúc xử lý");
                sc.close();
            }
        }
    }
}
