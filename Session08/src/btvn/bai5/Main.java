package btvn.bai5;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Thiết bị
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Sensor
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(fan);
        sensor.attach(ac);

        // Command đơn
        Command lightOff = new LightOffCommand(light);
        Command acSet = new ACSetTempCommand(ac, 28);
        Command fanLow = new FanLowCommand(fan);

        // Macro
        Command sleepMode = new SleepModeCommand(
                Arrays.asList(lightOff, acSet, fanLow)
        );

        // Remote
        RemoteControl remote = new RemoteControl();
        remote.setCommand(sleepMode);

        // 1. Bật chế độ ngủ
        remote.pressButton();

        // 2. Giả lập nhiệt độ tăng
        sensor.setTemperature(32);
    }
}
