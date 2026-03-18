package ra.presentation;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        runShowMenu();
    }

    private static void runShowMenu(){
        Scanner sc = new Scanner(System.in);
        UserView.showMenuUser();
    }
}
