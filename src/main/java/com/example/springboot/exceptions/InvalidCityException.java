package com.example.springboot.exceptions;

public class InvalidCityException extends Exception {
    private String cityName;

    public InvalidCityException(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String getMessage() { //when we call getMessage() function in this exception it will call this Override function of this function
        return "This \"" +  cityName + "\" is not a Valid City Name Numskull";

    }

}

