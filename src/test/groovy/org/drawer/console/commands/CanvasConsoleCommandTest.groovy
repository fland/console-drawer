package org.drawer.console.commands

import org.drawer.console.elements.Canvas
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class CanvasConsoleCommandTest extends Specification {

    def "should through CommandValidationException on 0 dimension"() {
        given:
        def canvasCommand = new CanvasConsoleCommand()

        when:
        def applicable = canvasCommand.isApplicable("C $width $height")
        canvasCommand.validate(Mock(Canvas))

        then:
        applicable
        thrown CommandValidationException

        where:
        width | height
        1     | 0
        0     | 2
    }

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
