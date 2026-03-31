package hoanhuy.validate;

import hoanhuy.utils.Color;

import java.util.Scanner;

public class Validator {
    public static boolean isValidate(String str){
        return str.trim().isBlank();
    }

    public static boolean isValidInt(int input){
        return input <= 0;
    }

    public static int getInt(Scanner sc , String input){
        do {
            try {
                System.out.print(input);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Color.RED + "Hãy nhập số nguyên ! " + Color.RESET);
            }
        } while(true);
    }

}
