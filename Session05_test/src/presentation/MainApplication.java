package presentation;

import exception.InvalidProductException;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) throws InvalidProductException {
        runProductView();
    }

    private static void runProductView() throws InvalidProductException {
        Scanner sc = new Scanner(System.in);
        ProductView.showProductView();
    }
}
