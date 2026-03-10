package re;

import java.util.Scanner;

public class InputMethod {
    private static Scanner input(){
        return new Scanner(System.in);
    }

    public static String inputString(String title){
        while(true){
            System.out.print(title);
            String str = input().nextLine();
            if (str.isBlank()){
                System.out.println("");
            }
        }
    }
}
