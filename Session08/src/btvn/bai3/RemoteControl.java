package btvn.bai3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RemoteControl {
    private Map<Integer, Command> buttons = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    public void setCommand(int slot, Command command) {
        buttons.put(slot, command);
        System.out.println("Đã gán " + command.getClass().getSimpleName() + " cho nút " + slot);
    }

    public void pressButton(int slot) {
        Command command = buttons.get(slot);
        if (command != null) {
            command.execute();
            history.push(command);
        } else {
            System.out.println("Nút chưa được gán!");
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            System.out.println("Undo thành công");
        } else {
            System.out.println("Không có gì để undo");
        }
    }
}