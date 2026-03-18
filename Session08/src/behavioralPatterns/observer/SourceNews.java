package behavioralPatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class SourceNews {
    private List<ReceiverObserver> observers = new ArrayList();

    public void addReceiver(ReceiverObserver observer) {
        observers.add(observer);
    }

    public void sendNews() {
        String content = "Nem chua rất ngon tôi đã gửi cho bạn !";
        for (ReceiverObserver observer : observers) {
            observer.update(content);
        }
    }
}
