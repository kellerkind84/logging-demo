package de.example.loggingdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class LoggingDemoApplication {

    final Logger logger = LogManager.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }

    @Scheduled(fixedRate = 1000)
    private void logSomething() {
        int i = (int) (Math.random() * 4);
        switch (i) {
            case 0:
                logger.info("This is an info message");
                break;
            case 1:
                logger.error("This is an error message");
                break;
            case 2:
                logger.debug("This is a debug message");
                break;
            case 3:
                throw new NullPointerException();
            default:
                logger.trace("Nothing to debug...");
                break;
        }
    }
}
