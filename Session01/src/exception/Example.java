package exception;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Example {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
//        int input = inputInterger();
//        System.out.println(input);

//        float input2 = inputFloat();
//        System.out.println(input2);

//        String input3 = inputString();
//        System.out.println(input3);

//        boolean input4 = inputBoolean();
//        System.out.println(input4);

        int age = inputPositive("Nhập tuổi : ");
    }

//    public static int inputInterger() throws MyUncheckedException{
//        Scanner sc = new Scanner(System.in);
//        while(true){
//            try{
//                System.out.print("Nhập vào 1 số nguyên : ");
//                int a = Integer.parseInt(sc.nextLine());
//                if (a <= 0){
//                    System.out.println("Số không được âm hãy nhập lại !");
//                    continue;
//                }
//                return a;
//            } catch (NumberFormatException e){
//                System.out.println("Bạn phải nhập 1 số nguyên !!!");
//
//            }
//        }
//    }
//
//    public static Float inputFloat() throws MyUncheckedException{
//        Scanner sc = new Scanner(System.in);
//        while(true){
//            try {
//                System.out.print("Nhập vào một số thực : ");
//                float a = Float.parseFloat(sc.nextLine());
//                if (a <= 0){
//                    System.out.println("Số không được âm hãy nhập lại !");
//                    continue;
//                }
//                return a;
//            } catch (NumberFormatException e){
//                System.out.println("Bạn phải nhập 1 số thực !!!");
//            }
//        }
//    }
//
//    public static String inputString() throws MyUncheckedException{
//        Scanner sc = new Scanner(System.in);
//        while(true){
//            try {
//                System.out.print("Nhập vào một chuỗi hoặc kí tự : ");
//                String a = sc.nextLine();
//                if (a.trim().length() <= 0){
//                    System.out.println("Không được để trống !");
//                    continue;
//                }
//                return a;
//            }catch (Exception  e){
//                System.out.println("Bạn phải nhập vào một chuỗi !!!");
//            }
//        }
//    }
//
//    public static boolean inputBoolean() throws MyUncheckedException{
//        Scanner sc = new Scanner(System.in);
//        while(true){
//            try {
//                System.out.print("Nhập vào kiểu boolean : ");
//                boolean a = Boolean.parseBoolean(sc.nextLine());
//                return a;
//            }catch (Exception  e){
//                System.out.println("Bạn phải nhập vào một boolean !!!");
//            }
//        }
//    }

    public static Scanner input(){
        return new Scanner(System.in);
    }

    public static int inputPositive(String title){
        System.out.printf("%s%s" , RED , title);
        while(true){
            try {
                int value = Integer.parseInt(input().nextLine());
                if ( value <= 0){
                    System.out.println("Không đươc âm !" + RED);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(RED + "Phải nhập số nguyên" + RESET);
            }
        }
    }
}
