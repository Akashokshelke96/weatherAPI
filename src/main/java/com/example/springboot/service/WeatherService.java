package com.example.springboot.service;


import com.example.springboot.accessor.WeatherAccessor;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherService {

    @Autowired
    public WeatherService(WeatherAccessor weatherAccessor) {
        this.weatherAccessor = weatherAccessor;
    }

    private WeatherAccessor weatherAccessor;

    public String getWeather(String city, Integer noOfDays) {
        try {
            HttpResponse<String> response = Unirest.get("https://weatherapi-com.p.rapidapi.com/forecast.json?q=" + city + "&days=" + noOfDays)
                    .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "8a6a4710a7msh85d1b8bd632cb36p1f1f29jsn594aa65e5195")
                    .asString();
            weatherAccessor.someMethod();
            return response.getBody();
        } catch (UnirestException e) {
            return e.getMessage();
        }
    }
}
