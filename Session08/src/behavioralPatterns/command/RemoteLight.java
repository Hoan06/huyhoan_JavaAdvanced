package behavioralPatterns.command;

public class RemoteLight {
    private LightCommand lightCommand;

    public void setLightCommand(LightCommand lightCommand) {
        this.lightCommand = lightCommand;
    }

    public void pressPowerButton(){
        lightCommand.execute();
    }

    public void pressUndoButton(){
        lightCommand.undo();
    }
}
