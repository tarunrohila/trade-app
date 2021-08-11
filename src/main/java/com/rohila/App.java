package com.rohila;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Class which is used to configure spring boot app
 *
 * @author Tarun Rohila
 */
@SpringBootApplication
public class App {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(App.class);


    /**
     * Method to initialize spring boot application.
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        try {
            SpringApplication.run(App.class, args);
        } catch (Exception e) {
            LOGGER.error("Failed to start Social Media App", e);
        }
    }
}
