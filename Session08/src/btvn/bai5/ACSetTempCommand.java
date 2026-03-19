package btvn.bai5;

public class ACSetTempCommand implements Command {
    private AirConditioner ac;
    private int temp;

    public ACSetTempCommand(AirConditioner ac, int temp) {
        this.ac = ac;
        this.temp = temp;
    }

    public void execute() {
        System.out.println("SleepMode: Điều hòa set " + temp + "°C");
        ac.setTemperature(temp);
    }
}