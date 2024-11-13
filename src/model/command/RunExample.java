package model.command;

import controller.Controller;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allSteps();
        } catch (Exception e) {
            System.out.println("Execution error: " + e.getMessage());
        }
    }
}
