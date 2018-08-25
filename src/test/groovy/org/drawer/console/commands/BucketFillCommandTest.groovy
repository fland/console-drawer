package org.drawer.console.commands

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class BucketFillCommandTest extends Specification {

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
