package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 * @see ConsoleCommand
 */

public abstract class AbstractCommand implements ConsoleCommand {

    String[] parameters = new String[0];
    private boolean valid = false;

    protected abstract String getPattern();

    @Override
    public boolean isApplicable(@NonNull String command) {
        if (!command.matches(getPattern())) {
            return false;
        }
        parameters = command.split(" ");
        valid = false;
        return true;
    }

    @Override
    public final Canvas execute(Canvas canvas) {
        if (!valid) {
            throw new CommandValidationException("Can't execute command. Validate parameters before executing command");
        }
        return executeCommand(canvas);
    }

    protected abstract Canvas executeCommand(Canvas canvas);

    @Override
    public final void validate(Canvas canvas) {
        validateCommand(canvas);
        valid = true;
    }

    protected abstract void validateCommand(Canvas canvas);
}
