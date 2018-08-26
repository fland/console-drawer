package org.drawer.console.commands;

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */

public final class CommandValidationException extends RuntimeException {

    public CommandValidationException(String message) {
        super(message);
    }
}
