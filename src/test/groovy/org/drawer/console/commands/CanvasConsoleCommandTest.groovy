package org.drawer.console.commands

import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class CanvasConsoleCommandTest extends Specification {

    def "should be applicable"() {
        given:
        def canvasCommand = new CanvasConsoleCommand()
        def command = 'C 2 4'

        when:
        def applicable = canvasCommand.isApplicable(command)

        then:
        applicable
    }
}
