package org.drawer.console.commands

import org.drawer.console.elements.Canvas
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */

class RectangleConsoleCommandTest extends Specification {

    @Unroll
    def "should throw CommandValidationException on coordinates [(#x1, #y1)(#x2, #y2)] out of canvas"() {
        given:
        def canvas = new Canvas(width, height, 2)
        def rectangleCommand = new RectangleConsoleCommand()

        when:
        def applicable = rectangleCommand.isApplicable("R $x1 $y1 $x2 $y2")
        rectangleCommand.validate(canvas)

        then:
        applicable
        thrown CommandValidationException

        where:
        x1 | x2 | y1 | y2 | width | height
        0  | 1  | 2  | 3  | 5     | 5
        1  | 0  | 2  | 3  | 5     | 5
        6  | 1  | 2  | 3  | 5     | 7
        1  | 6  | 2  | 3  | 5     | 7
        1  | 1  | 6  | 3  | 7     | 5
        1  | 1  | 2  | 6  | 7     | 5
    }

    @Unroll
    def "valid command '#command' should be applicable"() {
        given:
        def rectangleCommand = new RectangleConsoleCommand()

        when:
        def applicable = rectangleCommand.isApplicable(command)

        then:
        applicable

        where:
        command     | _
        'R 2 4 2 4' | _
        'R 0 5 1 2' | _
    }

    @Unroll
    def "invalid command '#command' should not be applicable"() {
        given:
        def rectangleCommand = new RectangleConsoleCommand()

        when:
        def applicable = rectangleCommand.isApplicable(command)

        then:
        !applicable

        where:
        command      | _
        'r 2 4 2 4'  | _
        'R  2 4 2 4' | _
        'R 2  4 2 4' | _
        'R 2 4  2 4' | _
        'R 2 4 2  4' | _
        ' R 2 4 2 4' | _
        'R 2 4 2 4 ' | _
        'R 2 z 2 4'  | _
    }
}
