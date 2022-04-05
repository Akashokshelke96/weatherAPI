package com.example.springboot;

import com.example.springboot.exceptions.InvalidCityException;
import com.example.springboot.service.WeatherService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}/{noOfDays}")
    public ResponseEntity<String> index(@PathVariable("city") String city, @PathVariable("noOfDays") Integer noOfDays) {
        try {
            JSONParser jsonParser = new JSONParser(); //creating a json parser object

            HttpResponse<String> unirestResponse = weatherService.getWeather(city, noOfDays);
            String result = unirestResponse.getBody();//result here is a JSON received from response

            Object obj = jsonParser.parse(result);
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject locationObj = (JSONObject) jsonObject.get("location");
            String name = (String) locationObj.get("name");
            String region = (String) locationObj.get("region");


            JSONObject currentObj = (JSONObject) jsonObject.get("current");
            double temp_c = (Double) currentObj.get("temp_c");

            System.out.println(" Name = " + name + " Region = " + region + " Temp_C " +temp_c);



//            HttpHeaders headers = new HttpHeaders();//next two lines we have added another header to the response code.
//            headers.add("Name", "Akash");

            return ResponseEntity.status(unirestResponse.getStatus()).body(result);
        } catch (InvalidCityException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        } catch (UnirestException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @GetMapping("/hello")
    public String index2() {
        return "this isn't the place for lagging behind in studies";
    }
}
