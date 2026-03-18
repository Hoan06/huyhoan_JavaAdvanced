package behavioralPatterns.observer;

public class Observer {
    public static void main(String[] args) {
        ReceiverObserver r1 = new ConcreteReceiver("Nguyễn Huy Hoàn");
        ReceiverObserver r2 = new ConcreteReceiver("An Hải Dũng");

        SourceNews vnExpress = new SourceNews();
        vnExpress.addReceiver(r1);
        vnExpress.addReceiver(r2);

        vnExpress.sendNews();

    }
}
