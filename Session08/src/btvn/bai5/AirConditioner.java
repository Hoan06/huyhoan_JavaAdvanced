package btvn.bai5;

import btvn.bai4.Observer;

public class AirConditioner implements Observer {
    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            System.out.println("Điều hòa: Nhiệt độ = " + temperature + " (giữ nguyên)");
        }
    }
}