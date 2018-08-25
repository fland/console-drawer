package org.drawer.console.commands

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class LineConsoleCommandTest extends Specification {

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
