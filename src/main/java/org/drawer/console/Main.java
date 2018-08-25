package org.drawer.console;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Drawer application started");
        System.out.println("Drawer application started.");
        Application application = new Application();
        application.start();
    }
}
