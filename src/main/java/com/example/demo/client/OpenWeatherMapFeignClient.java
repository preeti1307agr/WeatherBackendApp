package com.example.demo.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherMapFeignClient", url = "http://api.openweathermap.org")
public interface OpenWeatherMapFeignClient {

    @GetMapping(value = "/data/2.5/forecast", produces = "application/json")
    String getForecast(
            @RequestParam("q") String cityName,
            @RequestParam("appid") String applicationKey,
            @RequestParam("cnt") String count,
            @RequestParam("units") String unitsForTemp);
}
