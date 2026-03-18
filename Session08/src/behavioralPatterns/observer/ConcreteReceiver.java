package behavioralPatterns.observer;

public class ConcreteReceiver implements ReceiverObserver {
    private String name;

    public ConcreteReceiver(String name) {
        this.name = name;
    }


    @Override
    public void update(String news) {
        System.out.println("Bạn " + name + " đã nhận được tin : " + news);
    }
}
