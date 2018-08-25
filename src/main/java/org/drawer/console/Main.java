package org.drawer.console;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Hello from logs!");
        System.out.println("Hello world!");
        Application application = new Application();
        application.start();


    }
}
