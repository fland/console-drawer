package org.drawer.console.elements

import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */

class ColouredElementTest extends Specification {

    def "should return correct representation symbol"() {
        given:
        def expectedSymbol = 'f' as char
        def element = new ColouredElement(expectedSymbol)

        when:
        def actualSymbol = element.getRepresentation()

        then:
        actualSymbol == expectedSymbol
    }
}
