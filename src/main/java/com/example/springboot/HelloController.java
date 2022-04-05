package com.example.springboot;

import com.example.springboot.service.WeatherService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
	@Autowired
	private WeatherService weatherService;

	@GetMapping("/weather/{city}/{noOfDays}")
	public String index(@PathVariable("city") String city , @PathVariable("noOfDays") Integer noOfDays) {
		return  weatherService.getWeather(city,noOfDays);
	}

	@GetMapping("/hello")
	public String index2(){
		return "this isn't the place for lagging behind in studies";
	}
}
