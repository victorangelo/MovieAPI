package com.viktor;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Viktor Angelutsa
 *
 */
@SpringBootApplication
public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        logger.info("The application started.");
    }
}
