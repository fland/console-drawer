package org.drawer.console.elements

import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */

class EmptyElementTest extends Specification {

    def "should return correct representation symbol"() {
        given:
        def element = new EmptyElement()

        when:
        def symbol = element.getRepresentation()

        then:
        symbol == ' ' as char
    }
}
