package org.drawer.console.elements;

import lombok.RequiredArgsConstructor;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */
@RequiredArgsConstructor
public final class ColouredElement implements Element {

    private final char symbol;

    @Override
    public char getRepresentation() {
        return symbol;
    }
}
