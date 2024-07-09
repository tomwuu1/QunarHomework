package org.example;

import org.example.command.CatCommand;
import org.example.command.Command;
import org.example.command.GrepCommand;
import org.example.command.WcCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 93253
 */
public class Shell {
    public static void main(String[] args) throws IOException {
        Shell shell = new Shell();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command to execute:");
        String command = scanner.nextLine();
        shell.execute(command);
        /*shell.execute("cat xx.txt | grep xml | wc -l");
        shell.execute("cat xx.txt");*/

    }

    public void execute(String commandLine) throws IOException {
        String[] commands = commandLine.split("\\|");
        List<String> input = new ArrayList<>();

        for (String commandStr : commands) {
            Command command = parseCommand(commandStr.trim());
            input = command.execute(input);
        }

        input.forEach(System.out::println);
    }
    // todo:策略模式
    private Command parseCommand(String commandStr) {
        if (commandStr.startsWith("cat")) {
            String filename = commandStr.split(" ")[1];
            return new CatCommand(filename);
        } else if (commandStr.startsWith("grep")) {
            String keyword = commandStr.split(" ")[1];
            return new GrepCommand(keyword);
        } else if (commandStr.startsWith("wc -l")) {
            return new WcCommand();
        }
        throw new UnsupportedOperationException("Unknown command: " + commandStr);
    }
}