package presentation;

import java.util.Scanner;

public class MainApplycation {
    public static void main(String[] args) {
        runMenuProduct();
    }

    public static void runMenuProduct(){
        Scanner sc = new Scanner(System.in);
        ProductView.showMenuProduct();
    }
}
