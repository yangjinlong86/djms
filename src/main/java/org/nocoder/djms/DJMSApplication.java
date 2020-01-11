package org.nocoder.djms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author jason
 */
@ControllerAdvice
@SpringBootApplication
public class DJMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(DJMSApplication.class, args);
    }

}
