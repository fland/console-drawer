package org.drawer.console.commands

import org.drawer.console.elements.Canvas
import org.drawer.console.elements.CanvasHorizontalBorder
import org.drawer.console.elements.CanvasVerticalBorder
import org.drawer.console.elements.EmptyElement
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class CanvasConsoleCommandTest extends Specification {

    def "should create canvas"() {
        given:
        def canvasCommand = new CanvasConsoleCommand()
        def borderSize = 2
        def canvas = new Canvas(0, 0, borderSize)

        when:
        def applicable = canvasCommand.isApplicable("C $width $height")
        canvasCommand.validate(canvas)
        canvas = canvasCommand.execute(canvas)

        then:
        applicable
        canvas.height == height + borderSize
        canvas.width == width + borderSize
        canvas.getBorderSize() == borderSize
        for (x in 1..width - borderSize) {
            for (y in 1..height - borderSize) {
                canvas.elements[x][y] == new EmptyElement()
            }
        }
        for (x in 0..width) {
            canvas.elements[x][0] == new CanvasHorizontalBorder()
            canvas.elements[x][height] == new CanvasHorizontalBorder()
        }
        for (y in 0..height) {
            canvas.elements[0][y] == new CanvasVerticalBorder()
            canvas.elements[width][y] == new CanvasVerticalBorder()
        }

        where:
        width | height
        1     | 1
        5     | 5
        5     | 10
    }

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
