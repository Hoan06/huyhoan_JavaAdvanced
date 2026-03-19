package btvn.bai3;

public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Gán nút
        remote.setCommand(1, new LightOnCommand(light));
        remote.setCommand(2, new LightOffCommand(light));
        remote.setCommand(3, new ACSetTemperatureCommand(ac, 26));

        // Thực thi
        remote.pressButton(1);
        remote.pressButton(2);

        // Undo
        remote.undo();

        remote.pressButton(3);
        remote.undo();
    }
}