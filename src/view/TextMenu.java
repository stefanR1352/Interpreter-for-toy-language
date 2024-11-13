package view;

import model.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private final Map<String, Command> commands = new HashMap<>();

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    private void printMenu() {
        System.out.println("Menu:");
        for (Command command : commands.values()) {
            System.out.printf("%4s : %s\n", command.getKey(), command.getDescription());
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Select an option: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if (command == null) {
                System.out.println("Invalid option. Please try again.");
            } else {
                command.execute();
            }
        }
    }
}
