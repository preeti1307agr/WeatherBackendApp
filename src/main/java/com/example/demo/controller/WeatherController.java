package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.WeatherReport;
import com.example.demo.service.WeatherService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService  weatherService;
	
	
	@RequestMapping("/")
    String home() {
        return "Hello World!";
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/forecast/{city}")
	public List<WeatherReport> getWeatherForThreeDays(@PathVariable String city) throws ParseException {
		return this.weatherService.getWeatherForecast(city);		
	}

}
