package behavioralPatterns.command;

public class Command {
    public static void main(String[] args) {
        Light light = new Light();
        LightCommand on = new TurnOnLight(light);
        LightCommand off = new TurnOffLight(light);

        RemoteLight remoteLight = new RemoteLight();
        remoteLight.setLightCommand(off);
        remoteLight.pressPowerButton();
        remoteLight.pressUndoButton();
    }
}
