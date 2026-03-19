package btvn.bai5;

import btvn.bai4.Observer;

public class Fan implements Observer {
    private String speed = "Tắt";

    public void setLow() {
        speed = "Thấp";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void setHigh() {
        speed = "Mạnh";
        System.out.println("Quạt: Chạy tốc độ mạnh");
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            setHigh();
        }
    }
}
