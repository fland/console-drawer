package org.drawer.console.elements;

import lombok.EqualsAndHashCode;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

@EqualsAndHashCode
public final class EmptyElement implements Element {
    @Override
    public char getRepresentation() {
        return ' ';
    }
}
