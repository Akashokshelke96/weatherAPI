package com.example.springboot;

import com.example.springboot.exceptions.InvalidCityException;
import com.example.springboot.response.model.Weather;
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
   // public ResponseEntity<String> index(@PathVariable("city") String city, @PathVariable("noOfDays") Integer noOfDays) {
        public ResponseEntity<Weather> index(@PathVariable("city") String city, @PathVariable("noOfDays") Integer noOfDays) { //<weather> here is used while sending JSON
                                                                  //now the body will be just CITY-NAME & REGION-NAME FROM THE RECEIVED json.

        try {
            JSONParser jsonParser = new JSONParser(); //creating a json parser object

            HttpResponse<String> unirestResponse = weatherService.getWeather(city, noOfDays);
            String result = unirestResponse.getBody();//result here is a JSON received from response

            //consuming JSON using the easy preliminary way
            Object obj = jsonParser.parse(result);
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject locationObj = (JSONObject) jsonObject.get("location");//here we have put "location" from json to locationObj
            String name = (String) locationObj.get("name"); // //getting "name" from "location" value from json to this NAME value
            String region = (String) locationObj.get("region");


            JSONObject currentObj = (JSONObject) jsonObject.get("current"); // here we have put "current" from json to currentObj
            double temp_c = (Double) currentObj.get("temp_c");//temp_c is given in the JSON received so here pe put it under temp_c double variable.

            System.out.println(" Name = " + name + " Region = " + region + " Temp_C " +temp_c);

            Weather weather = new Weather(city,region);



//            HttpHeaders headers = new HttpHeaders();//next two lines we have added another header to the response code.
//            headers.add("Name", "Akash");

            return ResponseEntity.status(unirestResponse.getStatus()).body(weather); //earlier instead of weather result was used with @getmapping string
        } catch (InvalidCityException ex) {
          //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } catch (UnirestException ex) {
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()); //this one was used with the String
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ParseException e) {
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/hello")
    public String index2() {
        return "this isn't the place for lagging behind in studies";
    }
}
