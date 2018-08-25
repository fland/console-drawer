package org.drawer.console.commands

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */

class QuitConsoleCommandTest extends Specification {
    @Unroll
    def "valid command '#command' should be applicable"() {
        given:
        def quitCommand = new QuitConsoleCommand()

        when:
        def applicable = quitCommand.isApplicable(command)

        then:
        applicable

        where:
        command | _
        'Q'     | _
    }

    @Unroll
    def "invalid command '#command' should not be applicable"() {
        given:
        def quitCommand = new QuitConsoleCommand()

        when:
        def applicable = quitCommand.isApplicable(command)

        then:
        !applicable

        where:
        command | _
        'q'     | _
        ' Q'    | _
        'Q '    | _
    }
}
