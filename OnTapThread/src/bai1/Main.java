package bai1;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new NumberThread());
        Thread t2 = new Thread(new LetterThread());

        t1.start();
        t2.start();
    }
}
