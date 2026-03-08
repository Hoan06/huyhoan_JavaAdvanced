package btvn;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        System.out.println(inputInterger());;
    }

    public static int inputInterger(){
        try(Scanner sc = new Scanner(System.in);) {
            System.out.print("Nhập năm sinh : ");
            String input = sc.nextLine();
            int year =  Integer.parseInt(input);
            return year;
        } catch (NumberFormatException e) {
            System.out.println("Bạn phải nhập vào số nguyên !");
        }
        return -1;
    }
}
