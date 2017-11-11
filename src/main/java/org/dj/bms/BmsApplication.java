package org.dj.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author jason
 */
@ControllerAdvice
@SpringBootApplication
public class BmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsApplication.class, args);
	}

	// @ModelAttribute(name = "subject")
	// public Subject subject() {
	// return SecurityUtils.getSubject();
	// }
}
