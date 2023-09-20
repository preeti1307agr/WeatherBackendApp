package com.example.demo.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Declare a Feign client for making HTTP requests to the OpenWeatherMap API
@FeignClient(name = "openWeatherMapFeignClient", url = "http://api.openweathermap.org")
public interface OpenWeatherMapFeignClient {

    // Define a method for retrieving weather forecast data
    @GetMapping(value = "/data/2.5/forecast", produces = "application/json")
    String getForecast(
            @RequestParam("q") String cityName,
            @RequestParam("appid") String applicationKey,
            @RequestParam("cnt") String count,
            @RequestParam("units") String unitsForTemp);
}
