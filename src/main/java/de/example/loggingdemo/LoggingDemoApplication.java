package de.example.loggingdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class LoggingDemoApplication {

    final Logger logger = LogManager.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }

    @Scheduled(fixedRate = 1000)
    private void logSomething() {
        int i = (int) (Math.random() * 4);
        int k = (int) (Math.random() * 100);
        switch (i) {
            case 0:
                if (k > 30) {
                    logger.info("This is an info message\nit spans several\nlines");
                }
                break;
            case 1:
                if (k > 90) {
                    logger.error("This is an error message");
                }
                break;
            case 2:
                if (k > 50) {
                    logger.debug("This is a debug message");
                }
                break;
            case 3:
                if (k > 90) {
                    throw new NullPointerException();
                }
            default:
                logger.trace("Nothing to debug...");
                break;
        }
    }

    @RequestMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("hello World!");
    }

    @RequestMapping("/404test")
    public ResponseEntity<String> notFoundTest() {
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/internalerrortest")
    public ResponseEntity<String> internalErrorTest() {
        return ResponseEntity.status(500).body("InternalServerError");
    }
}
