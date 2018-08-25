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
public class Application {

    private final List<ConsoleCommand> commands;

    private Canvas canvas;

    public Application() {
        commands = List.of(new QuitConsoleCommand(), new CanvasConsoleCommand(), new LineConsoleCommand(),
                new BucketFillCommand(), new RectangleConsoleCommand());
        canvas = new Canvas(0, 0);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            log.info("Input command: {}", command);
            Optional<ConsoleCommand> consoleCommand = commands.stream()
                    .filter(i -> i.isApplicable(command))
                    .findFirst();

            consoleCommand.ifPresentOrElse(i -> execute(i, command), this::showUsage);
        }
    }

    private void execute(ConsoleCommand consoleCommand, String command) {
        canvas = consoleCommand.execute(command, canvas);
        System.out.println(canvas.toString());
    }

    private void showUsage() {
        System.out.println("Usage:\n" +
                "Command \t\tDescription\n" +
                "C w h           Should create a new canvas of width w and height h.\n" +
                "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only\n" +
                "                horizontal or vertical lines are supported. Horizontal and vertical lines\n" +
                "                will be drawn using the 'x' character.\n" +
                "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n" +
                "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n" +
                "                using the 'x' character.\n" +
                "B x y c         Should fill the entire area connected to (x,y) with \"colour\" c. The\n" +
                "                behaviour of this is the same as that of the \"bucket fill\" tool in paint\n" +
                "                programs.\n" +
                "Q               Should quit the program.");
    }
}
