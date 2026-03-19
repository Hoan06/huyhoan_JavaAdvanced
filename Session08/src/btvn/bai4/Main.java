package btvn.bai4;

public class Main {
    public static void main(String[] args) {

        TemperatureSensor sensor = new TemperatureSensor();

        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        // Đăng ký
        sensor.attach(fan);
        sensor.attach(humidifier);

        // Thay đổi nhiệt độ
        sensor.setTemperature(18);
        sensor.setTemperature(26);
    }
}
