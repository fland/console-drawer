package org.drawer.console.commands

import org.drawer.console.elements.Canvas
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class BucketFillCommandTest extends Specification {

    @Unroll
    def "should throw CommandValidationException on coordinates (#x, #y) out of canvas"() {
        given:
        def canvas = new Canvas(width, height, 2)
        def bucketFillCommand = new BucketFillCommand()

        when:
        def applicable = bucketFillCommand.isApplicable("B $x $y a")
        bucketFillCommand.validate(canvas)

        then:
        applicable
        thrown CommandValidationException

        where:
        x | y | width | height
        0 | 1 | 5     | 5
        1 | 0 | 5     | 5
        6 | 1 | 5     | 5
        1 | 6 | 5     | 5
    }

    @Unroll
    def "valid command '#command' should be applicable"() {
        given:
        def bucketFillCommand = new BucketFillCommand()

        when:
        def applicable = bucketFillCommand.isApplicable(command)

        then:
        applicable

        where:
        command    | _
        'B 2 4 f'  | _
        'B 50 5 1' | _
    }

    @Unroll
    def "invalid command '#command' should not be applicable"() {
        given:
        def bucketFillCommand = new BucketFillCommand()

        when:
        def applicable = bucketFillCommand.isApplicable(command)

        then:
        !applicable

        where:
        command     | _
        'b 2 4 f'   | _
        'B 50 5 $'  | _
        'B  50 5 d' | _
        'B 50  5 d' | _
        'B 50 5  d' | _
        'B 50 5 d ' | _
        ' B 50 5 d' | _
    }
}
