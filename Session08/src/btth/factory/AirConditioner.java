package btth.factory;

public class AirConditioner implements Device {

    @Override
    public void turnOn() {
        System.out.println("Bật điều hòa");
    }

    @Override
    public void turnOff() {
        System.out.println("Tắt điều hòa");
    }
}
