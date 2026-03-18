package behavioralPatterns.command;

public class TurnOnLight implements LightCommand {
    private Light light;

    public TurnOnLight(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
