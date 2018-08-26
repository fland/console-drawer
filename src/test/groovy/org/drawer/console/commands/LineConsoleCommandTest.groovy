package org.drawer.console.commands

import org.drawer.console.elements.Canvas
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class LineConsoleCommandTest extends Specification {

    @Unroll
    def "should throw CommandValidationException on coordinates [(#x1, #y1)(#x2, #y2)] out of canvas"() {
        given:
        def canvas = new Canvas(width, height)
        def lineCommand = new LineConsoleCommand()

        when:
        def applicable = lineCommand.isApplicable("L $x1 $y1 $x2 $y2")
        lineCommand.validate(canvas)

        then:
        applicable
        thrown CommandValidationException

        where:
        x1 | x2 | y1 | y2 | width | height
        0  | 1  | 2  | 3  | 5     | 5
        1  | 0  | 2  | 3  | 5     | 5
        6  | 1  | 2  | 3  | 5     | 5
        1  | 6  | 2  | 3  | 5     | 5
        1  | 1  | 6  | 3  | 5     | 5
        1  | 1  | 2  | 6  | 5     | 5
    }

    def "should throw CommandValidationException on oblique line"() {
        given:
        def canvas = new Canvas(5, 5)
        def lineCommand = new LineConsoleCommand()

        when:
        def applicable = lineCommand.isApplicable("L 1 1 2 3")
        lineCommand.validate(canvas)

        then:
        applicable
        thrown CommandValidationException
    }

    @Unroll
    def "valid command '#command' should be applicable"() {
        given:
        def lineCommand = new LineConsoleCommand()

        when:
        def applicable = lineCommand.isApplicable(command)

        then:
        applicable

        where:
        command     | _
        'L 2 4 2 4' | _
        'L 0 5 1 2' | _
    }

    @Unroll
    def "invalid command '#command' should not be applicable"() {
        given:
        def lineCommand = new LineConsoleCommand()

        when:
        def applicable = lineCommand.isApplicable(command)

        then:
        !applicable

        where:
        command      | _
        'l 2 4 2 4'  | _
        'L  2 4 2 4' | _
        'L 2  4 2 4' | _
        'L 2 4  2 4' | _
        'L 2 4 2  4' | _
        ' L 2 4 2 4' | _
        'L 2 4 2 4 ' | _
        'L 2 z 2 4'  | _
    }
}
