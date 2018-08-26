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

    protected abstract String getPattern();

    @Override
    public boolean isApplicable(@NonNull String command) {
        if (!command.matches(getPattern())) {
            return false;
        }
        parameters = command.split(" ");
        return true;
    }

    @Override
    public abstract Canvas execute(Canvas canvas);

    @Override
    public abstract void validate(Canvas canvas);
}
