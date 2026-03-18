package behavioralPatterns.command;

public interface LightCommand {
    void execute(); // thực thi
    void undo(); // hoàn tác
}
