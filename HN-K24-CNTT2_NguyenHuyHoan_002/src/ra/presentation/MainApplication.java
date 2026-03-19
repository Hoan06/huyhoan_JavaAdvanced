package ra.presentation;

import java.util.Scanner;

public class MainApplication{
    public static void main(String[] args) {
        runMenuEmployee();
    }

    private static void runMenuEmployee(){
        Scanner sc = new Scanner(System.in);
        EmployeeView.showMenuEmployee();
    }
}
