package org.drawer.console.commands

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class CanvasConsoleCommandTest extends Specification {

    @Unroll
    def "valid command '#command' should be applicable"() {
        given:
        def canvasCommand = new CanvasConsoleCommand()

        when:
        def applicable = canvasCommand.isApplicable(command)

        then:
        applicable

        where:
        command  | _
        'C 2 4'  | _
        'C 50 5' | _
        'C 0 5'  | _
    }

    @Unroll
    def "invalid command '#command' should not be applicable"() {
        given:
        def canvasCommand = new CanvasConsoleCommand()

        when:
        def applicable = canvasCommand.isApplicable(command)

        then:
        !applicable

        where:
        command   | _
        'C  2 4'  | _
        ' C 2 4'  | _
        'C 2 4 '  | _
        'C 5  5'  | _
        ' C 5 a ' | _
        'c 5 1'   | _
    }
}
