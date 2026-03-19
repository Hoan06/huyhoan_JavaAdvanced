package btvn.bai5;

public interface Subject {
    void attach(Observer o);
    void notifyObservers();
}