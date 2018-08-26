package org.drawer.console.commands

import org.drawer.console.elements.*
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class LineConsoleCommandTest extends Specification {

    def "should draw line"() {
        given:
        def width = 4
        def height = 7
        def borderSize = 2
        def canvas = new Canvas(width, height, borderSize)
        canvas.elements[1][2] = new ColouredElement('a' as char)
        canvas.elements[1][3] = new ColouredElement('a' as char)
        canvas.elements[1][4] = new LineElement()
        canvas.elements[1][5] = new LineElement()
        def lineCommand = new LineConsoleCommand()

        when:
        def applicable = lineCommand.isApplicable("L 1 2 1 4")
        lineCommand.validate(canvas)
        canvas = lineCommand.execute(canvas)

        then:
        applicable
        for (x in 0..width - 1) {
            canvas.elements[x][0] == new CanvasHorizontalBorder()
            canvas.elements[x][height - 1] == new CanvasHorizontalBorder()
        }
        for (y in 0..height - 1) {
            canvas.elements[0][y] == new CanvasVerticalBorder()
            canvas.elements[width - 1][y] == new CanvasVerticalBorder()
        }
        canvas.elements[1] == [new CanvasHorizontalBorder(),
                               new EmptyElement(), new LineElement(), new LineElement(), new LineElement(), new LineElement(),
                               new CanvasHorizontalBorder()] as Element[]
        canvas.elements[2] == [new CanvasHorizontalBorder(),
                               new EmptyElement(), new EmptyElement(), new EmptyElement(), new EmptyElement(), new EmptyElement(),
                               new CanvasHorizontalBorder()] as Element[]
    }

    @Unroll
    def "should throw CommandValidationException on coordinates [(#x1, #y1)(#x2, #y2)] out of canvas"() {
        given:
        def canvas = new Canvas(width, height, 2)
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
        6  | 1  | 2  | 3  | 5     | 7
        1  | 6  | 2  | 3  | 5     | 7
        1  | 1  | 6  | 3  | 7     | 5
        1  | 1  | 2  | 6  | 7     | 5
    }

    def "should throw CommandValidationException on oblique line"() {
        given:
        def canvas = new Canvas(5, 5, 2)
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
