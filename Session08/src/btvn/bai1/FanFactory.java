package btvn.bai1;

public class FanFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        return new Fan();
    }
}
