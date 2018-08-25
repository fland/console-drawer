package org.drawer.console.commands

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */

class RectangleConsoleCommandTest extends Specification {
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
