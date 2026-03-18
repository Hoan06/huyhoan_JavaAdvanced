package structuralPatterns.adapter;

public class HumanAdapter implements OldHuman {
    private YoungHuman old;

    public HumanAdapter(YoungHuman old) {
        this.old = old;
    }

    @Override
    public void move3foot() {
        old.move2foot();
        // mở rộng nó
        System.out.println("Chuyển sang đi bằng 3 chân");
    }
}
