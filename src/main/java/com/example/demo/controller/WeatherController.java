package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import com.example.demo.exception.WeatherServiceException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.WeatherReport;
import com.example.demo.service.WeatherService;

import javax.validation.constraints.Size;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService  weatherService;
	@RequestMapping("/")
    String home() {
        return "Hello World!";
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/forecast/{city}")
	public List<WeatherReport> getWeatherForThreeDays(@Size(min = 4, max = 100) @PathVariable String city) {
		try {
			return this.weatherService.getWeatherForecast(city);
		} catch (WeatherServiceException | ParseException ex) {
			throw new WeatherServiceException("Error while parsing weather data", ex);
		}
	}
}
