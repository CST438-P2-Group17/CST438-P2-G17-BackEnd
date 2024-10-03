package com.proj2g17.proj2g17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Proj2g17Application {
	@RequestMapping("/")
	public String home() {
		return "Hello Woorld!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Proj2g17Application.class, args);
	}

}
