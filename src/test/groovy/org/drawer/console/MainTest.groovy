package org.drawer.console

import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

class MainTest extends Specification {

    def "first test"() {
        given:
        def i = 0

        when:
        Main.main()

        then:
        i == 0
    }
}
