package com.example.springboot.accessor;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherAccessor {

        @Autowired
        public WeatherAccessor() {
        }

        public HttpResponse<String> getWeatherDetails(String city, int noOfDays) throws UnirestException {
            HttpResponse<String> response = Unirest.get("https://weatherapi-com.p.rapidapi.com/forecast.json?q=" + city + "&days=" + noOfDays)
                    .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                    .header("x-rapidapi-key", "f9b8f2034cmshb80b214ebe70b8bp1f83bdjsnc65527c6673b")
                    .asString();
            return response;
        }

    public void someMethod() {
    }
}

