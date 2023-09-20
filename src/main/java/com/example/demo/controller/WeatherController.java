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

	// Autowire the WeatherService for handling weather data.
	@Autowired
	private WeatherService  weatherService;

	// Define a simple home endpoint that returns "Hello World!"
	@RequestMapping("/")
    String home() {
        return "Hello World!";
	}

	// Enable Cross-Origin Resource Sharing (CORS) for requests from http://localhost:3000
	@CrossOrigin(origins = "http://localhost:3000")
	// Define a GET endpoint to fetch weather data for a specific city
	@GetMapping("/forecast/{city}")
	public List<WeatherReport> getWeatherForThreeDays(@Size(min = 4, max = 100) @PathVariable String city) {
		try {
			// Call the WeatherService to get weather forecast data for the specified city.
			return this.weatherService.getWeatherForecast(city);
		} catch (WeatherServiceException | ParseException ex) {
			// Handle exceptions related to weather data parsing and rethrow them as WeatherServiceException.
			throw new WeatherServiceException("Error while parsing weather data", ex);
		}
	}
}
