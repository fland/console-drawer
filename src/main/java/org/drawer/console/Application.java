package org.drawer.console;

import lombok.extern.slf4j.Slf4j;
import org.drawer.console.commands.*;
import org.drawer.console.elements.Canvas;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */
@Slf4j
final class Application {

    private static final String VALIDATION_ERROR_MESSAGE = "Command validation exception during execution: ";

    private final List<ConsoleCommand> commands;

    private Canvas canvas;

    Application() {
        commands = List.of(new QuitConsoleCommand(), new CanvasConsoleCommand(), new LineConsoleCommand(),
                new BucketFillCommand(), new RectangleConsoleCommand());
        canvas = new Canvas(0, 0, 0);
    }

    void start() {
        System.out.println("Provide command:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            log.info("Input command: {}", command);
            Optional<ConsoleCommand> consoleCommand = commands.stream()
                    .filter(i -> i.isApplicable(command))
                    .findFirst();

            consoleCommand.ifPresentOrElse(this::execute, this::showUsage);
            System.out.println("Provide command:");
        }
    }

    private void execute(ConsoleCommand consoleCommand) {
        try {
            consoleCommand.validate(canvas);
            canvas = consoleCommand.execute(canvas);
            System.out.println(canvas.toString());
        } catch (CommandValidationException e) {
            log.error(VALIDATION_ERROR_MESSAGE, e);
            System.out.println(VALIDATION_ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private void showUsage() {
        System.out.println("No such command. Usage:\n" +
                "Command \t\tDescription\n" +
                "C w h           Create a new canvas of width w and height h.\n" +
                "L x1 y1 x2 y2   Create a new line from (x1,y1) to (x2,y2). Currently only horizontal\n" +
                "                or vertical lines are supported. Horizontal and vertical lines will be drawn\n" +
                "                using the 'x' character.\n" +
                "R x1 y1 x2 y2   Create a new rectangle, whose upper left corner is (x1,y1) and lower right\n" +
                "                corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.\n" +
                "B x y c         Fill the entire area connected to (x,y) with single symbol \"colour\" c (a-Z or 0-1).\n" +
                "                The behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.\n" +
                "Q               Should quit the program.");
    }
}
