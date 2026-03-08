package btvn;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        System.out.println(calculate());
    }

    public static int calculate(){
        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Nhập vào tổng số người : ");
            int total =  sc.nextInt();
            System.out.print("Nhập vào số nhóm : ");
            int team =  sc.nextInt();
            int result = total / team;
            return result;
        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0 !.");
        }
        return -1;
    }
}
