package bai1;

public class LetterThread implements Runnable{
    @Override
    public void run() {
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println(c);
        }
    }
}
