package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot! WE SHOULD START LEARNING";
	}

	@GetMapping("/hello")
	public String index2(){
		return "this isnt the place for lagging behind in studies";
	}
}
