package behavioralPatterns.command;

public class TurnOffLight implements LightCommand {
    private Light  light;

    public TurnOffLight(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}
