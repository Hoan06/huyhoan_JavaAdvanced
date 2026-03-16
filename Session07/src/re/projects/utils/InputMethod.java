package re.projects.utils;

import re.projects.validate.InputValidator;

import java.util.Scanner;

public class InputMethod {
    public static String inputString(String title){
        do {
            Printer.printInfo(title);
            String value = input().nextLine();
//            if (InputValidator.isEmpty){
//                Printer.printError("");
//            } else {
//                return value;
//            }
        } while(true);
    }

    private static Scanner input(){
        return new Scanner(System.in);
    }
}
