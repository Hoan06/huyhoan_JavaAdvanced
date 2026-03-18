package btvn.bai1;

public class Light implements Device {
    @Override
    public void turnOn() {
        System.out.println("Đèn : bật sáng");
    }

    @Override
    public void turnOff() {
        System.out.println("Đèn : tắt ");
    }
}
